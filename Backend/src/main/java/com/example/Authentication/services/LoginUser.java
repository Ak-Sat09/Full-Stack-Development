package com.example.Authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.repositories.UserRepository;

@Service
public class LoginUser {
    
    @Autowired
    private UserRepository repository;

    public String loginUser(UserDto dto)throws Exception{
        if (!repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("User Not Found");
        }

        UserEntity user = repository.findByEmail(dto.getEmail()).get();
    
        boolean isCorrectPassword = PasswordEncoder.matchPassword(dto.getPassword(),user.getPassword());

        if (!isCorrectPassword) {
            throw new RuntimeException("Incorrect Password");
        }

        return "User Login Successfully";
    }
}
