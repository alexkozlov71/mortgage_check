package com.example.mortgage_check.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    private WebRequest webRequest;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
        webRequest = mock(WebRequest.class);
    }

    @Test
    public void testHandleGlobalException() {
        Exception exception = new Exception("Internal Server Error");
        ResponseEntity<?> response = globalExceptionHandler.handleGlobalException(exception, webRequest);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Internal Server Error", response.getBody());
    }

    @Test
    public void testHandleMaturityPeriodNotFoundException() {
        MaturityPeriodNotFoundException exception = new MaturityPeriodNotFoundException(
                "Rate not found for maturity period");
        ResponseEntity<?> response = globalExceptionHandler.handleMaturityPeriodNotFoundException(exception,
                webRequest);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Rate not found for maturity period", response.getBody());
    }
    @Test
    public void testHandleHttpMessageNotReadableException() {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("JSON parse error: Unrecognized field \"name\"", null, null);
        ResponseEntity<?> response = globalExceptionHandler.handleHttpMessageNotReadableException(exception, webRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("JSON parse error: Unrecognized field \"name\"", response.getBody());
    }
}