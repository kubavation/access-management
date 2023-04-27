package com.durys.jakub.accessmanagement.user.infrastructure;

import com.durys.jakub.accessmanagement.user.domain.UserConverter;
import com.durys.jakub.accessmanagement.user.domain.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUserConverter implements UserConverter<UserRepresentation> {

    private static final KeycloakUserConverter INSTANCE = new KeycloakUserConverter();

    public static KeycloakUserConverter instance() {
        return INSTANCE;
    }

    @Override
    public User toUser(UserRepresentation representation) {
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
