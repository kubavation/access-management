package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.user.UserConverter;
import com.durys.jakub.accessmanagement.user.model.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakRoleConverter implements UserConverter<RoleRepresentation> {

    private static final KeycloakRoleConverter INSTANCE = new KeycloakRoleConverter();

    public static KeycloakRoleConverter instance() {
        return INSTANCE;
    }

    @Override
    public User toUser(RoleRepresentation representation) {
        return new User(representation.getId(), representation.getUsername(), representation.getEmail(), representation.isEnabled());
    }

    @Override
    public UserRepresentation toRepresentation(User user) {
        UserRepresentation representation = new UserRepresentation();
        representation.setId(user.getId());
        representation.setUsername(user.getUsername());
        representation.setEmail(user.getEmail());
        representation.setEnabled(user.isEnabled());
        return representation;
    }
}
