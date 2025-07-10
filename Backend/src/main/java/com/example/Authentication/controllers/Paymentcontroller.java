 package com.example.Authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Authentication.services.Payment;

@RestController
@RequestMapping("/api/payment")
public class Paymentcontroller {

    @Autowired
    private Payment paymentService;

    // Endpoint: /api/payment/success?email=anmol@gmail.com
    @PostMapping("/success")
    public ResponseEntity<String> markPaymentDone(@RequestParam String email) {
        paymentService.pay(email);
        return ResponseEntity.ok("Payment successful for: " + email);
    }
}
