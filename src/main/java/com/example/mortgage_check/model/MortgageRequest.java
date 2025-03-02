package com.example.mortgage_check.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MortgageRequest {
    private Double income;
    private Integer maturityPeriod;
    private Double loanValue;
    private Double homeValue;
}
