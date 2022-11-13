package com.durys.jakub.accessmanagement.keycloak.configuration;


import lombok.NoArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class KeycloakExternalConfiguration {
    
    private final String keycloakServer;

    private final String keycloakRealm;

    private final String clientId;

    private final String secret;

    private final String externalLogin;
    private final String externalPassword;

    public KeycloakExternalConfiguration(@Value("${keycloak.auth-server-url}") String keycloakServer,
                                         @Value("${keycloak.realm}") String keycloakRealm,
                                         @Value("${keycloak.resource}")String clientId,
                                         @Value("${keycloak.credentials.secret}") String secret,
                                         @Value("${external-user.username}") String externalLogin,
                                         @Value("${external-user.password}") String externalPassword) {
        this.keycloakServer = keycloakServer;
        this.keycloakRealm = keycloakRealm;
        this.clientId = clientId;
        this.secret = secret;
        this.externalLogin = externalLogin;
        this.externalPassword = externalPassword;
    }

    @Bean
    Keycloak keycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServer)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakRealm).clientId(clientId)
                .username(externalLogin).password(externalPassword)
                .clientSecret(secret)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
    }

    @Bean
    RealmResource realmResource(Keycloak keycloakClient) {
        return keycloakClient.realm(keycloakRealm);
    }

}
