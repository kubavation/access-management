package com.durys.jakub.accessmanagement.shared.keycloak.model;


public record KeycloakUserCreatedResponse(String userId, String password, String email) {
}
