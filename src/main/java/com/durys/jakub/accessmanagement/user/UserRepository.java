package com.durys.jakub.accessmanagement.user;

import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;

import java.util.List;

public interface UserRepository {
    List<UserDTO> users();
    UserDTO userById(String id);
}
