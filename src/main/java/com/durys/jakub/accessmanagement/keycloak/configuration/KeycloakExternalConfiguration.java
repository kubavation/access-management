package com.durys.jakub.accessmanagement.keycloak.configuration;


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

    @Value("${keycloak.auth-server-url}")
    private String keycloakServer;

    @Value("${keycloak.realm}")
    private String keycloakRealm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String secret;



    @Bean
    Keycloak keycloakClient() {
        return KeycloakBuilder.builder()
                .serverUrl(keycloakServer)
                .grantType(OAuth2Constants.PASSWORD)
                .realm(keycloakRealm).clientId(clientId)
                .username("admin").password("admin")
                .clientSecret(secret)
                .resteasyClient(new ResteasyClientBuilder().connectionPoolSize(10).build())
                .build();
    }

    @Bean
    RealmResource realmResource(Keycloak keycloakClient) {
        return keycloakClient.realm(keycloakRealm);
    }

}
