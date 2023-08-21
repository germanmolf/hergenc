package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.domain.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public final class HeroesExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {HeroAlreadyExistsException.class})
    private ResponseEntity<Object> handleConflict(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {HeroNameNullException.class, HeroNameInvalidLengthException.class,
            HeroPowerNullException.class, HeroPowerInvalidLengthException.class})
    private ResponseEntity<Object> handleBadRequest(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {HeroNotFoundException.class})
    private ResponseEntity<Object> handleNotFound(RuntimeException e, WebRequest request) {
        return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
