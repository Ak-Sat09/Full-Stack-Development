package com.example.Authentication.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Authentication.entities.UserEntity;
import com.example.Authentication.exceptions.UserNotFound;
import com.example.Authentication.repositories.UserRepository;

@Service
public class Payment {

    @Autowired

    private UserRepository repository;


    public void pay(String email){
          Optional<UserEntity> optionalUser = repository.findByEmail(email);


        if (!optionalUser.isPresent()) {
            throw new UserNotFound("User is not registered yet");
        }

        UserEntity user = optionalUser.get();
        user.setPaid(true); 
        repository.save(user);
    }
    
}
