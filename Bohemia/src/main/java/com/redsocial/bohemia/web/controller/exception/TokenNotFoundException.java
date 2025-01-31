package com.redsocial.bohemia.web.controller.exception;


public class TokenNotFoundException extends RuntimeException {
    public TokenNotFoundException(String message) {
        super(message);
    }
}
