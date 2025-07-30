package com.example.Payment;

import com.razorpay.RazorpayException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody PaymentDto orderRequest) throws RazorpayException {
        Map<String, Object> order = paymentService.createOrder(orderRequest);

        ApiResponse response = ApiResponse.builder()
                .message("Order created successfully")
                .data(order)
                .status(PaymentStatus.SUCCESS)
                .build();

        return ResponseEntity.ok(response);
    }

    @PostMapping("/verify")
    public ResponseEntity<ApiResponse> verifyPayment(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam String signature) {

        log.info(" Received payment verification request");
        log.info(" Order ID: {}", orderId);
        log.info(" Payment ID: {}", paymentId);
        log.info(" Signature: {}", signature);

        try {
            boolean isValid = paymentService.verifySignature(orderId, paymentId, signature);
            log.info("Signature verification result: {}", isValid);

            if (isValid) {
                return ResponseEntity.ok(ApiResponse.builder()
                        .message("Payment verified successfully")
                        .status1(HttpStatus.OK)
                        .build());
            } else {
                log.warn(" Invalid payment signature!");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ApiResponse.builder()
                                .message("Invalid payment signature")
                                .status1(HttpStatus.BAD_REQUEST)
                                .build());
            }
        } catch (Exception e) {
            log.error(" Error during verification", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(ApiResponse.builder()
                            .message("Verification error: " + e.getMessage())
                            .status1(HttpStatus.INTERNAL_SERVER_ERROR)
                            .build());
        }
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updatePaymentStatus(
            @RequestParam String orderId,
            @RequestParam String paymentId,
            @RequestParam String status) {
        try {
            paymentService.updatePayment(orderId, paymentId, status.toUpperCase());

            return ResponseEntity.ok(ApiResponse.builder()
                    .message("Payment status updated successfully")
                    .build());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(ApiResponse.builder()
                            .message(e.getMessage())
                            .status1(HttpStatus.NOT_FOUND)
                            .build());
        }
    }
}
