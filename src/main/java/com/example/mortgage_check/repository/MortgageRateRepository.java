package com.example.mortgage_check.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.mortgage_check.model.Rate;


public interface MortgageRateRepository extends JpaRepository<Rate, Integer> {

}