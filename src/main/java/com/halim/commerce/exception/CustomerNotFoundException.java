package com.halim.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNotFoundException extends RuntimeException{
    private static final String DEFAULT_MESSAGE = "Worker could not find by id: ";

    public CustomerNotFoundException(Long id) {
        super(DEFAULT_MESSAGE + id.toString());
    }

    public CustomerNotFoundException(String message, Long id) {
        super(message + id.toString());
    }
}
