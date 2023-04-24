package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.user.UserConverter;
import com.durys.jakub.accessmanagement.user.model.dto.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUserConverter implements UserConverter<UserRepresentation> {

    @Override
    public User to(UserRepresentation representation) {
        return new User(representation.getId(), representation.getUsername(), representation.getEmail(), representation.isEnabled());
    }

    @Override
    public UserRepresentation to(User user) {
        UserRepresentation representation = new UserRepresentation();
        representation.setId(user.getId());
        representation.setUsername(user.getUsername());
        representation.setEmail(user.getEmail());
        representation.setEnabled(user.isEnabled());
        return representation;
    }
}
