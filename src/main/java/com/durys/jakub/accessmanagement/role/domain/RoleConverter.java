package com.durys.jakub.accessmanagement.role.domain;

import java.util.List;

public interface RoleConverter<T> {
    Role toRole(T t);
    T toRepresentation(Role role);

    default List<Role> toRoles(List<T> roles) {
        return roles.stream().map(this::toRole).toList();
    }

    default List<T> toRepresentations(List<Role> roles) {
        return roles.stream().map(this::toRepresentation).toList();
    }
}
