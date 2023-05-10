package com.durys.jakub.accessmanagement.user.domain;

import com.durys.jakub.accessmanagement.role.domain.Role;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> users();
    Optional<User> findById(String id);
    void save(User user);
    void delete(User user);
    List<Role> userRoles(String userId);
}
