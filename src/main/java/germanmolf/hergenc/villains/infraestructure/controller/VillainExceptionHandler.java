package germanmolf.hergenc.villains.infraestructure.controller;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.shared.infraestructure.controller.RestExceptionHandler;
import germanmolf.hergenc.villains.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public final class VillainExceptionHandler extends RestExceptionHandler {

    @ExceptionHandler(value = {VillainAlreadyExistsException.class})
    private ResponseEntity<Object> handleConflict(DomainError e) {
        return handleException(e, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {VillainNameNullException.class, VillainNameInvalidLengthException.class,
            VillainPowerNullException.class, VillainPowerInvalidLengthException.class})
    private ResponseEntity<Object> handleBadRequest(DomainError e) {
        return handleException(e, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(value = {VillainNotFoundException.class})
    private ResponseEntity<Object> handleNotFound(DomainError e) {
        return handleException(e, HttpStatus.NOT_FOUND);
    }
}
