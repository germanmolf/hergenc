package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.domain.exceptions.*;
import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.shared.infraestructure.controller.RestExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class HeroExceptionHandler extends RestExceptionHandler {

    @ExceptionHandler(value = {HeroAlreadyExistsException.class})
    private ResponseEntity<Object> handleConflict(DomainError e) {
        return handleException(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {HeroNameNullException.class, HeroNameInvalidLengthException.class,
            HeroPowerNullException.class, HeroPowerInvalidLengthException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {HeroNotFoundException.class})
    private ResponseEntity<Object> handleNotFound(DomainError e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }
}
