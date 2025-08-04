package com.example.Payment;

import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;

    private static final String RAZORPAY_KEY_ID = "rzp_test_qjbBqMLEcOwmiE";
    private static final String RAZORPAY_KEY_SECRET = "Dg8L3j9QFJYpZDkUqNkDvklk";

    // Create order on Razorpay and save it in DB
    public Map<String, Object> createOrder(PaymentDto dto) throws RazorpayException {
        RazorpayClient client = new RazorpayClient(RAZORPAY_KEY_ID, RAZORPAY_KEY_SECRET);

        JSONObject orderRequest = new JSONObject();
        orderRequest.put("amount", dto.getAmount() * 100); // paise
        orderRequest.put("currency", "INR");
        orderRequest.put("receipt", "txn_" + System.currentTimeMillis());

        Order order = client.orders.create(orderRequest);

        PaymentEntity payment = PaymentEntity.builder()
                .orderId(order.get("id"))
                .amount(dto.getAmount())
                .receiptId(order.get("receipt"))
                .status(PaymentStatus.CREATED)
                .createdAt(LocalDateTime.now())
                .build();

        paymentRepository.save(payment);

        Map<String, Object> response = new HashMap<>();
        response.put("orderId", order.get("id"));
        response.put("amount", order.get("amount"));
        response.put("currency", order.get("currency"));

        return response;
    }

    public boolean verifySignature(String orderId, String paymentId, String signature) throws Exception {
        String payload = orderId + "|" + paymentId;

        Mac mac = Mac.getInstance("HmacSHA256");
        SecretKeySpec secretKeySpec = new SecretKeySpec(RAZORPAY_KEY_SECRET.getBytes(), "HmacSHA256");
        mac.init(secretKeySpec);

        byte[] hashBytes = mac.doFinal(payload.getBytes());

        StringBuilder hashHex = new StringBuilder();
        for (byte b : hashBytes) {
            hashHex.append(String.format("%02x", b));
        }

        String generatedSignature = hashHex.toString();

        boolean isValid = generatedSignature.equals(signature);

        if (isValid) {
            // Update status in DB to SUCCESS
            PaymentEntity payment = paymentRepository.findByOrderId(orderId)
                    .orElseThrow(() -> new RuntimeException("Payment not found"));

            payment.setStatus(PaymentStatus.SUCCESS); // Or PAID if you want
            payment.setAttempts(payment.getAttempts() + 1); // Increment attempts if needed

            paymentRepository.save(payment);
        }

        return isValid;
    }

    // Update payment status and paymentId after successful payment
    public void updatePayment(String orderId, String paymentId, String status) {
        Optional<PaymentEntity> optionalPayment = paymentRepository.findByOrderId(orderId);
        if (optionalPayment.isEmpty()) {
            throw new RuntimeException("Payment not found with orderId: " + orderId);
        }

        PaymentEntity payment = optionalPayment.get();
        payment.setStatus(PaymentStatus.valueOf(status.toUpperCase()));

        paymentRepository.save(payment);
    }
}
