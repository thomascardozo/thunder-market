package com.tm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException validationException) {
        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), validationException.getMessage());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException authenticationException) {
        var details = new ExceptionDetails(HttpStatus.UNAUTHORIZED.value(), authenticationException.getMessage());
        return new ResponseEntity<>(details, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handleNoSuchElementException(NoSuchElementException noSuchElementException) {
        var details = new ExceptionDetails(HttpStatus.NOT_FOUND.value(), noSuchElementException.getLocalizedMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EstoqueNotFoundException.class)
    public ResponseEntity<?> handleBookNotFoundException(EstoqueNotFoundException bookNotFoundException) {
        var details = new ExceptionDetails(HttpStatus.NOT_FOUND.value(), bookNotFoundException.getLocalizedMessage());
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }
}
