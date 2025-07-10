 package com.example.Authentication.controllers;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Authentication.exceptions.ApiResponse;
import com.example.Authentication.exceptions.UserNotFound;
import com.example.Authentication.services.Payment;

@RestController
@RequestMapping("/api/payment")
@CrossOrigin(origins = "http://localhost:3000")
public class Paymentcontroller {

    @Autowired
    private Payment paymentService;
 
    @PostMapping("/success")
public ResponseEntity<ApiResponse> markPaymentDone(@RequestParam String email) {
    try {
        paymentService.pay(email);
        return ResponseEntity.ok(new ApiResponse(true, "Payment successful for: " + email));
    } catch (UserNotFound e) {
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
                .body(new ApiResponse(false, "User not found"));
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Payment failed"));
    }
}

}
