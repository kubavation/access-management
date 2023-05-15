package com.durys.jakub.accessmanagement.shared.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;


import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
public class KeycloakClient {

    private final RealmResource realmResource;

    public List<UserRepresentation> users() {
        return realmResource.users().list();
    }

    public Optional<UserRepresentation> userById(String id) {
        return users()
                .stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    public List<RoleRepresentation> roles() {
        return realmResource.roles().list();
    }

    public Optional<RoleRepresentation> roleById(String id) {
        return roles().stream()
                .filter(role -> role.getName().equals(id))
                .findFirst();
    }

    public static List<RoleRepresentation> roleNamesToRepresentations(List<String> roleNames) {
        return roleNames.stream()
                .map(roleName -> new RoleRepresentation(roleName, null, false))
                .toList();
    }
}
