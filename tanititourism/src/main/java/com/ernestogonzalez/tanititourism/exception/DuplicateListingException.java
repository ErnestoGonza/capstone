package com.ernestogonzalez.tanititourism.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;


public class DuplicateListingException extends RuntimeException{
    public DuplicateListingException(String message) {
        super(message);
    }
}
