package com.durys.jakub.accessmanagement.user.domain;

import java.util.List;

public interface UserRepository {
    List<User> users();
    User userById(String id);
    void delete(User user);
}
