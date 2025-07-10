package com.example.Authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.exceptions.ApiResponse;
import com.example.Authentication.services.RegisterUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000") 
public class RegisterUserController {
    
    @Autowired
    private RegisterUser registerUser;

   @PostMapping("/register")
public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody UserDto dto){
    try {
        String response = registerUser.saveUser(dto);
        return new ResponseEntity<>(new ApiResponse(true, response), HttpStatus.CREATED);
    } catch (Exception e) {
        return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), HttpStatus.BAD_REQUEST);
    }
}

}
