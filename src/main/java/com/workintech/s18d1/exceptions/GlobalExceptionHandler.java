package com.workintech.s18d1.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleBurgerException(BurgerException exception) {
        return ResponseEntity
                .status(exception.getHttpStatus())
                .body(new BurgerErrorResponse(exception.getMessage()));
    }

    @ExceptionHandler
    public ResponseEntity<BurgerErrorResponse> handleGenericException(Exception exception) {
        return ResponseEntity
                .internalServerError()
                .body(new BurgerErrorResponse(exception.getMessage()));
    }
}
