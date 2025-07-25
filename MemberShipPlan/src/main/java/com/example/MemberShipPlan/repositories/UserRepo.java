package com.example.MemberShipPlan.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.MemberShipPlan.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
