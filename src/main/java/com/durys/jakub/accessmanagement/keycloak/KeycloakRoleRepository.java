package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.RoleRepository;
import com.durys.jakub.accessmanagement.role.model.Role;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class KeycloakRoleRepository implements RoleRepository {

    private final KeycloakClientService keycloakClientService;

    @Override
    public List<Role> roles() {
        return KeycloakRoleConverter.instance().toRoles(keycloakClientService.roles());
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
        keycloakClientService.roles().add(representation);
    }

    @Override
    public void delete(Role role) {
        RoleRepresentation representation = KeycloakRoleConverter.instance().toRepresentation(role);
        keycloakClientService.roles().remove(representation);
    }
}
