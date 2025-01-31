package com.redsocial.bohemia.web.controller.exception;


public class LoginNotFoundException extends RuntimeException {
    public LoginNotFoundException(String message) {
        super(message);
    }
}
