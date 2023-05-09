package com.durys.jakub.accessmanagement.user.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> users();
    Optional<User> findById(String id);
    void save(User user);
    void delete(User user);
}
