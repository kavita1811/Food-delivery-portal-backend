package com.zomato.zomato.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity runtimeExceptionHandler(RuntimeException e) {
        return ResponseEntity.ok(e.getMessage());
    }
    @ExceptionHandler(NoRestaurantsAvailableException.class)
    public ResponseEntity noRestaurantsAvailableExceptionHandler(NoRestaurantsAvailableException e) {
        return ResponseEntity.ok(e.getMessage());
    }
}
