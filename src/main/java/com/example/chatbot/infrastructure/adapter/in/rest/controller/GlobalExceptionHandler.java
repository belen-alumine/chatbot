package com.example.chatbot.infrastructure.adapter.in.rest.controller;

import com.example.chatbot.domain.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandler {
    public record ErrorResponse(int statusCode, String message) {}

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage()); // Sin sobrescribir métodos
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}