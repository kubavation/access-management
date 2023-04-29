package com.durys.jakub.accessmanagement.role.domain;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> roles();
    Optional<Role> findById(String id);
    void save(Role role);
    void delete(Role role);
}