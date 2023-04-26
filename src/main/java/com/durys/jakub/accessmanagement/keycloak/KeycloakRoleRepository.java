package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.RoleRepository;
import com.durys.jakub.accessmanagement.role.model.Role;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class KeycloakRoleRepository implements RoleRepository {

    private final KeycloakClientService keycloakClientService;

    @Override
    public List<Role> roles() {
        return null;
    }

    @Override
    public Optional<Role> findById(String id) {
        return Optional.empty();
    }

    @Override
    public void save(Role role) {

    }

    @Override
    public void delete(Role role) {

    }
}
