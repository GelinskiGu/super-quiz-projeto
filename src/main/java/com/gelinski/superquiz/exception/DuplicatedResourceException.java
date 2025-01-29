package com.gelinski.superquiz.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DuplicatedResourceException extends RuntimeException {
    public DuplicatedResourceException(String ex) {
        super(ex);
    }
}
