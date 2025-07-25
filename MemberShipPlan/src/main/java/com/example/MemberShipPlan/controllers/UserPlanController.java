package com.example.MemberShipPlan.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemberShipPlan.dtos.UserPlanDto;
import com.example.MemberShipPlan.entities.User;
import com.example.MemberShipPlan.entities.planEntity;
import com.example.MemberShipPlan.services.UserPlanService;

@RestController
@RequestMapping("/userplans")
public class UserPlanController {

    @Autowired
    private UserPlanService service;

    @PostMapping("/buy")
    public ResponseEntity<String> buyPlan(@RequestBody UserPlanDto dto) {
        return ResponseEntity.ok(service.buyPlan(dto));
    }

    @GetMapping("/users/{planId}")
    public ResponseEntity<List<User>> getUsersByPlan(@PathVariable Long planId) {
        return ResponseEntity.ok(service.getUsersByPlanId(planId));
    }

    @GetMapping("/plans/{userId}")
    public ResponseEntity<List<planEntity>> getPlansByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(service.getPlansByUserId(userId));
    }
}
