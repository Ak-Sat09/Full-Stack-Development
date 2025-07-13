package com.example.Authentication.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.Authentication.entities.UserEntity;

public interface RefferalRepositories extends JpaRepository<UserEntity, Long> {

    // 🔍 Find the user by their unique referral code
    UserEntity findByReferralCode(String code);

    // 🔍 Find all users referred by a given person’s name
    List<UserEntity> findByReferredByName(String name);

}
