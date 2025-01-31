package com.redsocial.bohemia.web.controller.exception;


public class ReactionNotFoundException extends RuntimeException {
    public ReactionNotFoundException(String message) {
        super(message);
    }
}
