package com.durys.jakub.accessmanagement.shared.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Component;

import java.util.List;


@RequiredArgsConstructor
@Component
public class KeycloakClient {

    private final RealmResource realmResource;

    public List<UserRepresentation> users() {
        return realmResource.users().list();
    }

    public List<RoleRepresentation> roles() {
        return realmResource.roles().list();
    }
}
