package com.durys.jakub.accessmanagement.role.infrastructure;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClient;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.infrastructure.KeycloakUserConverter;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class KeycloakRoleRepository implements RoleRepository {

    private final KeycloakClient keycloakClient;

    @Override
    public List<Role> roles() {
        return KeycloakRoleConverter.instance()
                .toRoles(keycloakClient.rolesResource().list());
    }

    @Override
    public Optional<Role> findById(String id) {
        return keycloakClient.rolesResource().list()
                .stream()
                .filter(role -> role.getName().equals(id))
                .map(role -> KeycloakRoleConverter.instance().toRole(role))
                .findFirst();
    }

    @Override
    public void save(Role role) {
        RoleRepresentation representation = KeycloakRoleConverter.instance().toRepresentation(role);
        keycloakClient.rolesResource().create(representation);
    }

    @Override
    public void delete(Role role) {
        RoleRepresentation representation = KeycloakRoleConverter.instance().toRepresentation(role);
        keycloakClient.rolesResource().deleteRole(representation.getId());
    }

    @Override
    public List<User> usersWithRole(String roleName) {
        return keycloakClient.usersResource().list()
                .stream()
                .filter(user -> user.getRealmRoles().contains(roleName))
                .map(KeycloakUserConverter.instance()::toUser)
                .toList();
    }
}
