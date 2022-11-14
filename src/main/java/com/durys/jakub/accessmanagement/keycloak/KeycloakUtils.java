package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import org.keycloak.representations.idm.RoleRepresentation;

public class KeycloakUtils {

    public static RoleRepresentation toKeycloakRoleRepresentation(RoleDTO role) {
        return new RoleRepresentation(role.getName(), role.getDescription(), false);
    }
}
