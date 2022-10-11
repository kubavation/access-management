package com.durys.jakub.accessmanagement.user.exception;

public class UserLockedException extends RuntimeException {
    private static final String MSG = "User with username %s is locked";

    public UserLockedException(String username) {
        super(String.format(MSG, username));
    }
}
