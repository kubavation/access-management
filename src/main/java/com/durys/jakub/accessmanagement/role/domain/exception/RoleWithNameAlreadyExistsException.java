package com.durys.jakub.accessmanagement.role.domain.exception;

public class RoleWithNameAlreadyExistsException extends RuntimeException {

    private static final String MSG = "Role with name %s already exists";

    public RoleWithNameAlreadyExistsException(String role) {
        super(String.format(MSG, role));
    }
}
