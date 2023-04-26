package com.durys.jakub.accessmanagement.role;

import com.durys.jakub.accessmanagement.role.model.Role;

import java.util.List;
import java.util.Optional;

public interface RoleRepository {
    List<Role> roles();
    Optional<Role> findById(String id);
    void save(Role role);
    void delete(Role role);
}
