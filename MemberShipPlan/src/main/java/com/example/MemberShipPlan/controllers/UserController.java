package com.example.MemberShipPlan.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.MemberShipPlan.dtos.UserDto;
import com.example.MemberShipPlan.entities.User;
import com.example.MemberShipPlan.services.UserService;

@RestController
public class UserController {

    private final UserService userService;

    // Constructor injection (preferred)
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/save")
    public String saveUser(@RequestBody UserDto user) {
        userService.save(user);
        return "User Saved";
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(User user) {
        List<User> users = userService.getAllUsers(user);
        return ResponseEntity.ok(users);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<User> getUserById(@PathVariable Long id) throws
    // UserNotFoundException {
    // User user = userService.getById(id)
    // .orElseThrow(() -> new UserNotFoundException("User not found with id: " +
    // id));
    // return ResponseEntity.ok(user);
    // }

    // @DeleteMapping("/deleteAll")
    // public ResponseEntity<String> deleteAllUsers() {
    // String message = userService.deleteAllUsers();
    // return ResponseEntity.ok(message);
    // }

    // @DeleteMapping("/delete/{id}")
    // public ResponseEntity<String> deleteUserById(@PathVariable Long id) {
    // String message = userService.deleteById(id);
    // return ResponseEntity.ok(message);
    // }
}
