package com.durys.jakub.accessmanagement.shared.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RolesResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;


@RequiredArgsConstructor
public class KeycloakClient {

    private final RealmResource realmResource;

    public UsersResource usersResource() {
        return realmResource.users();
    }

    public RolesResource rolesResource() {
        return realmResource.roles();
    }


    public static List<RoleRepresentation> roleNamesToRepresentations(List<String> roleNames) {
        return roleNames.stream()
                .map(roleName -> new RoleRepresentation(roleName, null, false))
                .toList();
    }
}
