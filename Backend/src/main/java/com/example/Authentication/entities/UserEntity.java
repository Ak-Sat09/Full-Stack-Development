package com.example.Authentication.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;

    @Builder.Default
    private boolean isPaid = false;

    @Column(nullable = true)
    private String referralCode;

    @Column(name = "referred_by_name", nullable = true)
    private String referredByName;

}
