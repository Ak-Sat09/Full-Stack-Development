package com.example.MemberShipPlan.dtos;

import java.util.List;

import com.example.MemberShipPlan.entities.planEntity.planEntityBuilder;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PlanDto {
    private Long id;
    private String name;
    private String description;
    private double price;
    private List<String> features;

    public static planEntityBuilder builder() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'builder'");
    }

}
