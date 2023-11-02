package com.tm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionGlobalHandler {

    @ExceptionHandler(CurrencyUnsupportedException.class)
    public ResponseEntity<?> handleCurrencyUnsupportedException(CurrencyUnsupportedException unsupportedException) {
        var details = new ExceptionDetails(HttpStatus.NOT_FOUND.value(), "Moeda nao suportada");
        return new ResponseEntity<>(details, HttpStatus.NOT_FOUND);
    }

}
