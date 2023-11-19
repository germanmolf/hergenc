package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.shared.infraestructure.controller.RestExceptionHandler;
import com.example.heroes.villains.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public final class VillainExceptionHandler extends RestExceptionHandler {

    @ExceptionHandler(value = {VillainAlreadyExistsException.class})
    private ResponseEntity<Object> handleConflict(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {VillainNameNullException.class, VillainNameInvalidLengthException.class,
            VillainPowerNullException.class, VillainPowerInvalidLengthException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.BAD_REQUEST, request);

    }

    @ExceptionHandler(value = {VillainNotFoundException.class})
    private ResponseEntity<Object> handleNotFound(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.NOT_FOUND, request);
    }
}
