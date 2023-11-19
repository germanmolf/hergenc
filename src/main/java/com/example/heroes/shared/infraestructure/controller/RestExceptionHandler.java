package com.example.heroes.shared.infraestructure.controller;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.shared.domain.exceptions.IdentifierNotValidException;
import com.example.heroes.shared.domain.exceptions.IdentifierNullException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IdentifierNullException.class, IdentifierNotValidException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e, WebRequest request) {
        return handleException(e, HttpStatus.BAD_REQUEST, request);
    }

    protected ResponseEntity<Object> handleException(DomainError e, HttpStatus httpStatus, WebRequest request) {
        return handleExceptionInternal(e, ErrorResponse.fromDomainError(e), new HttpHeaders(), httpStatus, request);
    }
}
