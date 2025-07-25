package com.example.MemberShipPlan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MemberShipPlan.entities.planEntity;

public interface PlanRepo extends JpaRepository<planEntity, Long> {

}
