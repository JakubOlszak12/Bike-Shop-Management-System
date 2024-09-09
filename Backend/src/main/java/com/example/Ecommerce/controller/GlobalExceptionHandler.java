package com.example.Ecommerce.controller;

import com.example.Ecommerce.Exception.ErrorResponse;
import com.example.Ecommerce.Exception.UserException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException ex) {
        // Create an error response with the message from UserError
        ErrorResponse errorResponse = new ErrorResponse(ex.getUserError().getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    // handle other exceptions here
}

