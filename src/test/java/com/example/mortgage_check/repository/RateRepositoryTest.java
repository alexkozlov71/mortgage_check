package com.example.mortgage_check.repository;

import com.example.mortgage_check.model.Rate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class RateRepositoryTest {

    @Autowired
    private MortgageRateRepository rateRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    public void setUp() {
        jdbcTemplate.execute("DELETE FROM RATE");
    }

    @Test
    public void testFindAll() {
        Rate rate1 = new Rate(10, 3.0, Timestamp.from(Instant.now()));
        Rate rate2 = new Rate(15, 3.5, Timestamp.from(Instant.now()));
        rateRepository.save(rate1);
        rateRepository.save(rate2);

        List<Rate> rates = rateRepository.findAll();
        assertThat(rates).hasSize(2).containsExactlyInAnyOrder(rate1, rate2);
    }

    @Test
    public void testFindById() {
        Rate rate = new Rate(10, 3.0, Timestamp.from(Instant.now()));
        rateRepository.save(rate);

        Rate foundRate = rateRepository.findById(10).orElse(null);
        assertThat(foundRate).isNotNull();
        assertThat(foundRate.getMaturityPeriod()).isEqualTo(10);
        assertThat(foundRate.getInterestRate()).isEqualTo(3.0);
    }
}