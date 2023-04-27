package com.durys.jakub.accessmanagement.user.domain.exception;

public class UsernameAlreadyExistsException extends RuntimeException {
    private static final String MSG = "User with username %s already exists";

    public UsernameAlreadyExistsException(String username) {
        super(String.format(MSG, username));
    }
}
