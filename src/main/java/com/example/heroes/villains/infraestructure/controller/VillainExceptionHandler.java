package com.example.heroes.villains.infraestructure.controller;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.shared.infraestructure.ErrorResponse;
import com.example.heroes.villains.domain.exceptions.VillainAlreadyExistsException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public final class VillainExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {VillainAlreadyExistsException.class})
    private ResponseEntity<Object> handleConflict(DomainError e, WebRequest request) {
        return handleExceptionInternal(e, ErrorResponse.fromDomainError(e), new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

}
