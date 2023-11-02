package com.tm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CurrencyUnsupportedException extends RuntimeException {

    public CurrencyUnsupportedException(String message) {
        super(message);
    }
}
