package com.durys.jakub.accessmanagement.user.domain;

import com.durys.jakub.accessmanagement.user.domain.User;

import java.util.List;

public interface UserConverter<T> {
    User toUser(T t);
    T toRepresentation(User user);

    default List<User> toUsers(List<T> users) {
        return users.stream().map(this::toUser).toList();
    }

    default List<T> toRepresentations(List<User> users) {
        return users.stream().map(this::toRepresentation).toList();
    }
}
