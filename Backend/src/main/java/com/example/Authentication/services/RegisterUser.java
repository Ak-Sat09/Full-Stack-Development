package com.example.Authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.exceptions.UserAlreadyException;
import com.example.Authentication.repositories.UserRepository;

@Service
public class RegisterUser {
    
    @Autowired
    private UserRepository repository;

    public String saveUser(UserDto dto) throws Exception{
        if (repository.findByEmail(dto.getEmail()).isPresent()) {
            throw new UserAlreadyException("User Already Exists");
        }

        UserEntity user = UserEntity.builder().name(dto.getName()).email(dto.getEmail()).password(PasswordEncoder.passwordEncode(dto.getPassword())).build();
 

        user.setPaid(false);

        repository.save(user);

        return "User Registerd Succesfully";
         }
}
