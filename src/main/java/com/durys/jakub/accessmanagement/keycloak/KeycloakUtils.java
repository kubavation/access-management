package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class KeycloakUtils {

    public static RoleRepresentation toKeycloakRoleRepresentation(RoleDTO role) {
        return new RoleRepresentation(role.getName(), role.getDescription(), false);
    }

    public static UserRepresentation toKeycloakUserRepresentation(CreateUserRequest user) {

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(user.getUsername());
        userRepresentation.setEmail(user.getEmail());
        userRepresentation.setFirstName(user.getFirstName());
        userRepresentation.setLastName(user.getLastName());
        userRepresentation.setRealmRoles(user.getRoles().stream().map(RoleDTO::getName).toList());
        userRepresentation.setEnabled(true);

        return userRepresentation;
    }
}
