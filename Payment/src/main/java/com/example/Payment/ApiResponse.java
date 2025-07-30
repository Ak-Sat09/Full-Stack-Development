package com.example.Payment;

import org.springframework.http.HttpStatus;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApiResponse {
    private String message;
    private Object data;
    private PaymentStatus status;
    private HttpStatus status1;
}