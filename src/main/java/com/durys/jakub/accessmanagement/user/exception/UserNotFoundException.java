package com.durys.jakub.accessmanagement.user.exception;

public class UserNotFoundException extends RuntimeException {
    private static final String MSG = "User with username %s not found";

    public UserNotFoundException(String username) {
        super(String.format(MSG, username));
    }
}
