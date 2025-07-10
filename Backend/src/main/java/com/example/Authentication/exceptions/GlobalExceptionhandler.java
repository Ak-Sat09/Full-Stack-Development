package com.example.Authentication.exceptions;

import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionhandler {

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<ApiResponse> handleUserNotFound(UserNotFound ex) {
        return ResponseEntity.status(HttpStatus.SC_NOT_FOUND)
                .body(new ApiResponse(false, ex.getMessage()));
    }

    @ExceptionHandler(UserAlreadyException.class)
    public ResponseEntity<ApiResponse> handleUserAlready(UserAlreadyException ex) {
        return ResponseEntity.status(HttpStatus.SC_BAD_REQUEST)
                .body(new ApiResponse(false, ex.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.SC_UNAUTHORIZED)
                .body(new ApiResponse(false, ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(false, "Something went wrong"));
    }
}
