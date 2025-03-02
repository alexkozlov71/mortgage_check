package com.example.mortgage_check.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "RATE")
@EqualsAndHashCode
public class Rate {
    @Id
    @Column(name = "maturity_period")
    private int maturityPeriod;
    @Column(name = "interest_rate")
    private double interestRate;
    @Column(name = "last_update")
    private Timestamp lastUpdate;
}
