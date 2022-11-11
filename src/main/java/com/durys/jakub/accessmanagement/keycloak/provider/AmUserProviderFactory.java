package com.durys.jakub.accessmanagement.keycloak.provider;


import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class AmUserProviderFactory implements UserStorageProviderFactory<AmUserStorageProvider> {

    private static final String PROVIDER_ID = "AM-USER-PROVIDER";


    @Override
    public AmUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        return new AmUserStorageProvider(keycloakSession, componentModel);
    }

    @Override
    public String getId() {
        return PROVIDER_ID;
    }
}
