package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.heroes.domain.exceptions.HeroPowerInvalidLengthException;
import com.example.heroes.heroes.domain.exceptions.HeroPowerNullException;
import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.shared.infraestructure.controller.ErrorResponse;
import com.example.heroes.versus.domain.exceptions.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public final class VersusExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {VersusAlreadyExistsException.class, HeroAlreadyDefeatedException.class, VillainAlreadyDefeatedException.class})
    private ResponseEntity<Object> handleConflict(DomainError e, WebRequest request) {
        return handleExceptionInternal(e, ErrorResponse.fromDomainError(e), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(value = {VersusDefeatedNullException.class, VersusDefeatedInvalidValueException.class,
            HeroPowerNullException.class, HeroPowerInvalidLengthException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e, WebRequest request) {
        return handleExceptionInternal(e, ErrorResponse.fromDomainError(e), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {VersusNotFoundException.class})
    private ResponseEntity<Object> handleNotFound(DomainError e, WebRequest request) {
        return handleExceptionInternal(e, ErrorResponse.fromDomainError(e), new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
