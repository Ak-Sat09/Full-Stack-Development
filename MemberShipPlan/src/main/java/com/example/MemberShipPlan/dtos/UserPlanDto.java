package com.example.MemberShipPlan.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserPlanDto {

    private Long userId; // User's id
    private Long planId; // Plan's id
    // private LocalDate startDate;
    // private LocalDate endDate;
}
