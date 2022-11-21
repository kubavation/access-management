package com.durys.jakub.accessmanagement.keycloak.model;


public record KeycloakUserCreatedResponse(String userId, String password, String email) {
}
