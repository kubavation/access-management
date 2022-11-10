package com.durys.jakub.accessmanagement.shared.exception;


public class EntityNotFoundException extends RuntimeException {
    private static final String MSG = "Entity of class %s and id %s not found";

    public EntityNotFoundException(Class<?> clazz, Long id) {
        super(String.format(MSG, clazz.getSimpleName(), id));
    }

    public EntityNotFoundException(Class<?> clazz, String id) {
        super(String.format(MSG, clazz.getSimpleName(), id));
    }
}
