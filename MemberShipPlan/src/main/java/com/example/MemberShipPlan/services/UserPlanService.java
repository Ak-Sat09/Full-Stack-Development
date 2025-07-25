package com.example.MemberShipPlan.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MemberShipPlan.dtos.UserPlanDto;
import com.example.MemberShipPlan.entities.User;
import com.example.MemberShipPlan.entities.UserPlan;
import com.example.MemberShipPlan.entities.planEntity;
import com.example.MemberShipPlan.repositories.PlanRepo;
import com.example.MemberShipPlan.repositories.UserPlanRepo;
import com.example.MemberShipPlan.repositories.UserRepo;

@Service
public class UserPlanService {

    @Autowired
    private UserPlanRepo userPlanRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PlanRepo planRepo;

    public String buyPlan(UserPlanDto dto) {

        User user = userRepo.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        planEntity plan = planRepo.findById(dto.getPlanId())
                .orElseThrow(() -> new RuntimeException("Plan not found"));

        UserPlan userPlan = UserPlan.builder()
                .user(user)
                .plan(plan)
                .build();

        userPlanRepo.save(userPlan);

        return "Plan bought successfully!";
    }

    public List<User> getUsersByPlanId(Long planId) {
        List<UserPlan> userPlans = userPlanRepo.findByPlanId(planId);
        return userPlans.stream().map(UserPlan::getUser).collect(Collectors.toList());
    }

    // Get all plans bought by a specific user
    public List<planEntity> getPlansByUserId(Long userId) {
        List<UserPlan> userPlans = userPlanRepo.findByUserId(userId);
        return userPlans.stream().map(UserPlan::getPlan).collect(Collectors.toList());
    }
}
