package com.durys.jakub.accessmanagement.shared.keycloak;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;

import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
class KeycloakPasswordGenerator {

    @AllArgsConstructor
    @Getter
    private enum CredentialsType {
        PASSWORD_TYPE("password");

        private final String value;
    }

    private static String generatePassword() {
        return UUID.randomUUID().toString();
    }

    static CredentialRepresentation credentialRepresentation() {
        CredentialRepresentation credentialRepresentation = new CredentialRepresentation();
        credentialRepresentation.setTemporary(true);
        credentialRepresentation.setType(CredentialsType.PASSWORD_TYPE.value);
        credentialRepresentation.setValue(generatePassword());
        return credentialRepresentation;
    }
}
