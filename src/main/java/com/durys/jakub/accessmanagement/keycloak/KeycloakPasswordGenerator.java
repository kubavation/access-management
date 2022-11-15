package com.durys.jakub.accessmanagement.keycloak;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class KeycloakPasswordGenerator {

    static String generate() {
        return UUID.randomUUID().toString();
    }
}
