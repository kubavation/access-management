package com.durys.jakub.accessmanagement.role.infrastructure;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClientService;
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
