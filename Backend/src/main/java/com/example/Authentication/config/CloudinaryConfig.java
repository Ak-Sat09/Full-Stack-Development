package com.example.Authentication.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = new HashMap<>();
        config.put("cloud_name", "dcnejt8lw");
        config.put("api_key", "841613979743688");
        config.put("api_secret", "D0MlrShmMSPhz55bapgeM_e3kX0");
        return new Cloudinary(config);
    }
}
