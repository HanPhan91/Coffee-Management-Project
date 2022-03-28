package com.cg.coffeemanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class EmailExistsException extends RuntimeException   {
    private static final long serialVersionUID = 1L;

    public EmailExistsException(String message) {
        super(message);
    }
}
