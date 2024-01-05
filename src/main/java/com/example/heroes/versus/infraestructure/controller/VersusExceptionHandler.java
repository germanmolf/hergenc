package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.shared.infraestructure.controller.RestExceptionHandler;
import com.example.heroes.versus.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public final class VersusExceptionHandler extends RestExceptionHandler {

    @ExceptionHandler(value = {VersusAlreadyExistsException.class, HeroAlreadyDefeatedException.class, VillainAlreadyDefeatedException.class})
    private ResponseEntity<Object> handleConflict(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {VersusDefeatedNullException.class, VersusDefeatedInvalidValueException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {VersusNotFoundException.class})
    private ResponseEntity<Object> handleNotFound(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.NOT_FOUND, request);
    }
}
