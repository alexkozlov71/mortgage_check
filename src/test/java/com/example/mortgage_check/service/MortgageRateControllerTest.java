package com.example.mortgage_check.service;

import com.example.mortgage_check.model.MortgageRequest;
import com.example.mortgage_check.model.Rate;
import com.example.mortgage_check.repository.MortgageRateRepository;
import com.example.mortgage_check.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.closeTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public class MortgageRateControllerTest {

    @Mock
    private MortgageRateRepository rateRepository;

    @InjectMocks
    private MortgageRateController mortgageRateController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(mortgageRateController)
                .setControllerAdvice(new GlobalExceptionHandler())
                .build();
    }

    @Test
    public void testGetRates() throws Exception {
        Rate rate1 = new Rate(10, 3.0, Timestamp.from(Instant.now()));
        Rate rate2 = new Rate(15, 3.5, Timestamp.from(Instant.now()));
        when(rateRepository.findAll()).thenReturn(Arrays.asList(rate1, rate2));

        mockMvc.perform(get("/api/interest-rates"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .json("[{'maturityPeriod':10,'interestRate':3.0},{'maturityPeriod':15,'interestRate':3.5}]"));
    }

    @Test
    public void testCheckMortgage() throws Exception {
        MortgageRequest request = new MortgageRequest(10000.00, 10, 12000.00, 20000.00);
        Rate rate = new Rate(10, 3.0, Timestamp.from(Instant.now()));
        when(rateRepository.findById(anyInt())).thenReturn(Optional.of(rate));

        mockMvc.perform(post("/api/mortgage-check")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"income\":10000,\"maturityPeriod\":10,\"loanValue\":12000,\"homeValue\":20000}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.feasible").value(true))
                .andExpect(jsonPath("$.monthlyCost", closeTo(115.87, 0.01)));
    }

    @Test
    public void testCheckMortgageRateNotFound() throws Exception {
        when(rateRepository.findById(anyInt())).thenReturn(Optional.empty());

        mockMvc.perform(post("/api/mortgage-check")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"income\":10000,\"maturityPeriod\":10,\"loanValue\":12000,\"homeValue\":20000}"))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Rate not found for maturity period: 10"));
    }
}