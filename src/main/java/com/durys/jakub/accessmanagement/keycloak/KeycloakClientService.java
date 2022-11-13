package com.durys.jakub.accessmanagement.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
class KeycloakClientService {

    private final RealmResource realmResource;

    public List<UserRepresentation> getUsers() {
        return realmResource.users().list();
    }

    public UserRepresentation getUser(String id) {
        return Optional.ofNullable(realmResource.users().get(id))
                .map(UserResource::toRepresentation)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }
}
