package com.example.Authentication.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.repositories.UserRepository;

@Service
public class LoginUser {

    @Autowired
    private UserRepository repository;

    public Map<String, Object> loginUser(UserDto dto) {
        UserEntity user = repository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        boolean isCorrectPassword = PasswordEncoder.matchPassword(dto.getPassword(), user.getPassword());

        if (!isCorrectPassword) {
            throw new RuntimeException("Incorrect password");
        }

        if (!user.isPaid()) {
            throw new RuntimeException("Payment not completed. Please complete payment to login.");
        }

        Map<String, Object> response = new HashMap<>();
        response.put("message", "User login successful");
        response.put("referralCode", user.getReferralCode()); // <-- add referralCode here

        return response;
    }
}
