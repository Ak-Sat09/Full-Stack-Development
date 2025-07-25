package com.example.MemberShipPlan.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MemberShipPlan.entities.UserPlan;

public interface UserPlanRepo extends JpaRepository<UserPlan, Long> {

    List<UserPlan> findByUserId(Long userId);

    List<UserPlan> findByPlanId(Long planId);

}
