package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.RoleConverter;
import com.durys.jakub.accessmanagement.role.model.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
        return new RoleRepresentation(role.name(), role.description(), false);
    }
}
