package com.example.Authentication.dtos;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDto {

    private String name;

    @NotBlank(message = "Email is Required")
    @Email(message = "Invalid Email Format")
    private String email;

    @NotBlank(message = "Password is Required")
    @Size(min = 6, max = 8, message = "Password must be 6 to 8 characters")
    private String password;

    @Column(nullable = true)
    private String referralCode;

    @Column(nullable = true)
    private String referredBy;

}
