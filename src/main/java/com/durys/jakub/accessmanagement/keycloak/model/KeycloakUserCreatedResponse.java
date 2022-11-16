package com.durys.jakub.accessmanagement.keycloak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KeycloakUserCreatedResponse {
    private final String userId;
    private final String password;
    private final String email;
}
