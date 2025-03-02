package com.example.mortgage_check.service;

import com.example.mortgage_check.exception.MaturityPeriodNotFoundException;
import com.example.mortgage_check.model.MortgageCheckResponse;
import com.example.mortgage_check.model.MortgageRequest;
import com.example.mortgage_check.model.Rate;
import com.example.mortgage_check.repository.MortgageRateRepository;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for handling mortgage rate related requests.
 */
@RestController
@RequestMapping(value = "/api")
@AllArgsConstructor
public class MortgageRateController {
    private final MortgageRateRepository repository;

    /**
     * Retrieves a list of current interest rates.
     *
     * @return a list of {@link Rate} objects
     */
    @GetMapping(value = "/interest-rates")
    public List<Rate> getRates() {
        return repository.findAll();
    }

    /**
     * Checks the feasibility of a mortgage request and calculates the monthly cost.
     *
     * @param mortgageRequest the mortgage request containing income, maturity
     *                        period, loan value, and home value
     * @return a {@link MortgageCheckResponse} containing the feasibility and
     *         monthly cost
     */
    @PostMapping(value = "/mortgage-check", produces = "application/json")
    public MortgageCheckResponse checkMortgage(@RequestBody MortgageRequest mortgageRequest) {
        return checkLoan(mortgageRequest);
    }

    /**
     * Helper method to check the feasibility of a mortgage request and calculate
     * the monthly cost.
     *
     * @param mortgageRequest the mortgage request containing income, maturity
     *                        period, loan value, and home value
     * @return a {@link MortgageCheckResponse} containing the feasibility and
     *         monthly cost
     */
    private MortgageCheckResponse checkLoan(MortgageRequest mortgageRequest) {

        Rate rate = repository.findById(mortgageRequest.getMaturityPeriod())
                .orElseThrow(() -> new MaturityPeriodNotFoundException("Rate not found for maturity period: "
                        + mortgageRequest.getMaturityPeriod()));

        boolean feasible = mortgageRequest.getLoanValue() <= 4 * mortgageRequest.getIncome() &&
                mortgageRequest.getLoanValue() <= mortgageRequest.getHomeValue();

        // Calculate monthly cost using the formula for monthly mortgage payments
        double monthlyInterestRate = rate.getInterestRate() / 100 / 12;
        int numberOfPayments = mortgageRequest.getMaturityPeriod() * 12;
        double monthlyCost = 0;

        if (monthlyInterestRate != 0) {
            monthlyCost = (mortgageRequest.getLoanValue() * monthlyInterestRate) /
                    (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments));
        } else {
            monthlyCost = mortgageRequest.getLoanValue() / numberOfPayments;
        }

        return new MortgageCheckResponse(feasible, monthlyCost);
    }
}
