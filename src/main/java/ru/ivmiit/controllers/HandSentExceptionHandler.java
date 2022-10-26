package ru.ivmiit.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.ivmiit.dto.response.ErrorResponse;
import ru.ivmiit.exceptions.HandSentException;

@ControllerAdvice
public class HandSentExceptionHandler {

    @ExceptionHandler(HandSentException.class)
    public ResponseEntity<ErrorResponse> handleException(HandSentException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(
                ErrorResponse.builder().status(exception.getHttpStatus()).message(exception.getMessage()).build()
        );
    }
}