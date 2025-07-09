package com.example.Authentication.exceptions;

public class UserAlreadyException extends RuntimeException{
     public UserAlreadyException(String message) {
        super(message);
    }
}
