 package com.example.Authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Authentication.dtos.UserDto;
import com.example.Authentication.exceptions.UserNotFound;
import com.example.Authentication.services.LoginUser;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginUserController {

    @Autowired
    private LoginUser loginUser;

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@Valid @RequestBody UserDto userDto) {
        try {
            String response = loginUser.loginUser(userDto);
            return ResponseEntity.ok(response); // cleaner
        } catch (UserNotFound e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Missing required fields");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage()); // show error msg like "Incorrect password" or "Payment not completed"
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
        }
    }
}
