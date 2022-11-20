package com.durys.jakub.accessmanagement.keycloak.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public record KeycloakUserCreatedResponse(String userId, String password, String email) {
}
