package com.durys.jakub.accessmanagement.shared.keycloak;

import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.role.domain.Role;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class KeycloakRoleRepository implements RoleRepository {

    private final KeycloakClient keycloakClient;

    @Override
    public List<Role> roles() {
        return KeycloakRoleConverter.instance().toRoles(keycloakClient.roles());
    }

    @Override
    public Optional<Role> findById(String id) {
        return roles().stream()
                .filter(r -> r.name().equals(id))
                .findFirst();
    }

    @Override
    public void save(Role role) {
        RoleRepresentation representation = KeycloakRoleConverter.instance().toRepresentation(role);
        keycloakClient.roles().add(representation);
    }

    @Override
    public void delete(Role role) {
        RoleRepresentation representation = KeycloakRoleConverter.instance().toRepresentation(role);
        keycloakClient.roles().remove(representation);
    }
}
