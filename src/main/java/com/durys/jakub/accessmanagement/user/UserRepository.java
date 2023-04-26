package com.durys.jakub.accessmanagement.user;

import com.durys.jakub.accessmanagement.user.model.dto.User;

import java.util.List;

public interface UserRepository {
    List<User> users();
    User userById(String id);
    void delete(User user);
}
