package germanmolf.hergenc.shared.infraestructure.controller;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.shared.domain.exceptions.IdentifierNotValidException;
import germanmolf.hergenc.shared.domain.exceptions.IdentifierNullException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {IdentifierNullException.class, IdentifierNotValidException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e) {
        return handleException(e, HttpStatus.BAD_REQUEST);
    }

    protected ResponseEntity<Object> handleException(DomainError e, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(ErrorResponse.fromDomainError(e));
    }
}
