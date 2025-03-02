package com.example.mortgage_check.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;

/**
 * Global exception handler for handling various exceptions in the application.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles generic exceptions.
     *
     * @param ex      the exception
     * @param request the web request
     * @return a response entity with the error message and HTTP status code 500
     *         (Internal Server Error)
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    /**
     * Handles maturity period not found exceptions.
     *
     * @param ex      the exception
     * @param request the web request
     * @return a response entity with the error message and HTTP status code 404
     *         (Not Found)
     */
    @ExceptionHandler(MaturityPeriodNotFoundException.class)
    public ResponseEntity<?> handleMaturityPeriodNotFoundException(MaturityPeriodNotFoundException ex,
            WebRequest request) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles HTTP message not readable exceptions.
     *
     * @param ex      the exception
     * @param request the web request
     * @return a response entity with the error message and HTTP status code 400
     *         (Bad Request)
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex,
            WebRequest request) {
        String errorMessage = ex.getMessage();
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
