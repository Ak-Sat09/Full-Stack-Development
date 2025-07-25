package com.example.MemberShipPlan.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.MemberShipPlan.dtos.PlanDto;
import com.example.MemberShipPlan.entities.planEntity;
import com.example.MemberShipPlan.repositories.PlanRepo;

@Service
public class PlanService {

    private static final Logger logger = LoggerFactory.getLogger(PlanService.class);

    @Autowired
    private PlanRepo repo;

    public PlanDto save(PlanDto plan) {

        // 🖨️ Log user input
        logger.info("👉 User Entered Plan Data: {}", plan);

        planEntity plan1 = planEntity.builder()
                .name(plan.getName())
                .description(plan.getDescription())
                .price(plan.getPrice())
                .features(plan.getFeatures())
                .validityInDays(30)
                .build();

        repo.save(plan1);

        logger.info("✅ Plan saved successfully: {}", plan1.getName());

        return plan;
    }

}
