package com.redsocial.bohemia.web.controller.exception;


public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException(String message) {
        super(message);
    }
}
