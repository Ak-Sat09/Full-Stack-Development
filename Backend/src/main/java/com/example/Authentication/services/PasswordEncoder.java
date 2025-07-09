package com.example.Authentication.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoder {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    
    public static String passwordEncode(String plainPassword){
        return encoder.encode(plainPassword);
    }

    public static boolean matchPassword(String plainPassword , String hashedPassword){
        return encoder.matches(plainPassword, hashedPassword);
    }
}
