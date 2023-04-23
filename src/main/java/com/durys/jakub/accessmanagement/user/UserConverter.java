package com.durys.jakub.accessmanagement.user;

import com.durys.jakub.accessmanagement.user.model.dto.User;

public interface UserConverter<T> {
    User to(T t);
    T to(User user);
}
