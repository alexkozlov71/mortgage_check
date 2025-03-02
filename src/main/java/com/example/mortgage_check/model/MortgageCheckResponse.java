package com.example.mortgage_check.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class MortgageCheckResponse {
    private boolean feasible;
    private double monthlyCost;
}