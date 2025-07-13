package com.example.Authentication.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.exceptions.UserAlreadyException;
import com.example.Authentication.repositories.RefferalRepositories;
import com.example.Authentication.repositories.UserRepository;

@Service
public class RegisterUser {

    @Autowired
    private UserRepository repository;

    @Autowired
    private RefferalRepositories refferalRepositories;

    @Transactional
    public String saveUser(UserDto dto) throws Exception {

        System.out.println("Registering user with email: " + dto.getEmail());

        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyException("User already exists");
        }

        String referredByName = "";

        String referredByCode = dto.getReferralCode();
        System.out.println("Referral code provided: " + referredByCode);

        if (referredByCode != null && !referredByCode.isBlank()) {
            UserEntity referrer = refferalRepositories.findByReferralCode(referredByCode);
            if (referrer != null) {
                referredByName = referrer.getName();
                System.out.println("Referrer found with name: " + referredByName);
            } else {
                System.out.println("No referrer found with code: " + referredByCode);
                // Optionally, you can throw error here or just continue
            }
        } else {
            System.out.println("No referral code provided.");
        }

        UserEntity user = UserEntity.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(PasswordEncoder.passwordEncode(dto.getPassword()))
                .isPaid(false)
                .referredByName(referredByName) // This must match entity field
                .referralCode(generateUnique4Digit())
                .build();

        repository.save(user);
        System.out.println("User saved with referredByName: " + referredByName);

        return "User registered successfully";
    }

    private String generateUnique4Digit() {
        Random random = new Random();
        String code;
        do {
            code = String.valueOf(1000 + random.nextInt(9000));
        } while (refferalRepositories.findByReferralCode(code) != null);
        return code;
    }
}
