package com.durys.jakub.accessmanagement.user;

import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;

public interface UserConverter<T> {
    UserDTO to(T t);
    T to(UserDTO userDTO);
}
