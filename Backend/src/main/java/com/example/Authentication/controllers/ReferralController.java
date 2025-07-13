package com.example.Authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.Authentication.dtos.RefferalNodeDto;
import com.example.Authentication.services.Refferalservice;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ReferralController {

    @Autowired
    private Refferalservice refferalservice;

    @GetMapping("/{code}")
    public ResponseEntity<RefferalNodeDto> getNetwork(@PathVariable String code) {
        RefferalNodeDto tree = refferalservice.getReferralTree(code); // service now returns DTO
        if (tree == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(tree);
    }

}
