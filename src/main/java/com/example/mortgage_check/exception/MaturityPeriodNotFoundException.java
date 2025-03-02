package com.example.mortgage_check.exception;

/**
 * Exception thrown when a maturity period is not found in the repository.
 */
public class MaturityPeriodNotFoundException extends RuntimeException {

    /**
     * Constructs a new MaturityPeriodNotFoundException with the specified detail
     * message.
     *
     * @param message the detail message
     */
    public MaturityPeriodNotFoundException(String message) {
        super(message);
    }
}
