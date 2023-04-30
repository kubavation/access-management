package com.durys.jakub.accessmanagement.role.infrastructure;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleConverter;
import org.keycloak.representations.idm.RoleRepresentation;

public class KeycloakRoleConverter implements RoleConverter<RoleRepresentation> {

    private static final KeycloakRoleConverter INSTANCE = new KeycloakRoleConverter();

    public static KeycloakRoleConverter instance() {
        return INSTANCE;
    }

    @Override
    public Role toRole(RoleRepresentation representation) {
        return new Role(representation.getName(), representation.getDescription());
    }

    @Override
    public RoleRepresentation toRepresentation(Role role) {
        RoleRepresentation representation = new RoleRepresentation();
        representation.setName(role.name());
        representation.setDescription(role.description());
        return representation;
    }
}
