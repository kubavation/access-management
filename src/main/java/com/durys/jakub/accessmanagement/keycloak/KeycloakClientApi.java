package com.durys.jakub.accessmanagement.keycloak;

import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeycloakClientApi {

    private final KeycloakClientService keycloakClientService;

    public List<UserRepresentation> getUsers() {
        return keycloakClientService.getUsers();
    }

    public UserRepresentation getUser(String id) {
        return keycloakClientService.getUser(id);
    }
}
