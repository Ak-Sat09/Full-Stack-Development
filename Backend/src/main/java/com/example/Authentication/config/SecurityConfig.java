package com.example.Authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @SuppressWarnings("removal")
    @Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .csrf().disable()
        .authorizeHttpRequests()
            .requestMatchers("/api/v1/register","/api/v1/login" ,  "/api/v1/upload" ,  "/api/payment/**" ).permitAll() 
            .anyRequest().authenticated()
        .and()
        .httpBasic();

    return http.build();
}

    
}
