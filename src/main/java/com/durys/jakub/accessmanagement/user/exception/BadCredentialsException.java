package com.durys.jakub.accessmanagement.user.exception;

public class BadCredentialsException extends RuntimeException {
    private static final String MSG = "Incorrect username or password";

    public BadCredentialsException() {
        super(MSG);
    }
}
