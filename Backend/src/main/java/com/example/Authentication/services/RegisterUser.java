package com.example.Authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.repositories.UserRepository;

@Service
public class RegisterUser {
    
    @Autowired
    private UserRepository repository;

    public String saveUser(UserDto dto) throws Exception{
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new Exception("User already exists with this email");
        }

        UserEntity user = new UserEntity();

        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setPassword(PasswordEncoder.passwordEncode(dto.getPassword()));

         repository.save(user);

        return "User Registerd Succesfully";
         }
}
