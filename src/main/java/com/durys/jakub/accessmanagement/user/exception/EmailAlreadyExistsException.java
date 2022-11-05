package com.durys.jakub.accessmanagement.user.exception;

public class EmailAlreadyExistsException extends RuntimeException {
    private static final String MSG = "User with email %s already exists";

    public EmailAlreadyExistsException(String email) {
        super(String.format(MSG, email));
    }
}
