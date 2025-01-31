package com.redsocial.bohemia.web.controller.exception;


public class DuplicateEntityException extends RuntimeException {
    public DuplicateEntityException(String message) {
        super(message);
    }
}
