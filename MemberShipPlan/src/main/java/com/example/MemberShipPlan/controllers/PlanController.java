package com.example.MemberShipPlan.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.MemberShipPlan.dtos.PlanDto;
import com.example.MemberShipPlan.services.PlanService;

@RestController
public class PlanController {

    @Autowired
    private PlanService service;

    @PostMapping("/plan")
    public PlanDto createPlan(@RequestBody PlanDto planDto) {
        service.save(planDto);
        return planDto;
    }

}
