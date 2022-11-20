package com.durys.jakub.accessmanagement.keycloak;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class KeycloakPasswordGenerator {

    private static final String PASSWORD_TYPE = "password";

    private static String generatePassword() {
        return UUID.randomUUID().toString();
    }

    static CredentialRepresentation credentialRepresentation() {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(true);
        credentialRepresentation.setType(PASSWORD_TYPE);
        credentialRepresentation.setValue(generatePassword());
        return credentialRepresentation;
    }
}
