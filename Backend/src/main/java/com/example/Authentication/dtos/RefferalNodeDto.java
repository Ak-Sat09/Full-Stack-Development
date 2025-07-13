package com.example.Authentication.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RefferalNodeDto {

    private String name;
    private List<RefferalNodeDto> children = new ArrayList<>();

    public RefferalNodeDto(String name) {
        this.name = name;
    }
}
