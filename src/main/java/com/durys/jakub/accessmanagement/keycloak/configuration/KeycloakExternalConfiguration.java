package com.durys.jakub.accessmanagement.keycloak.configuration;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class KeycloakExternalConfiguration {

    @Value("${keycloak.auth-server-url}")
    private String keycloakServer;

    @Value("${keycloak.realm}")
    private String keycloakRealm;


    @Bean(name = "keycloakClient")
    public WebClient keycloakClient() {
       return WebClient.builder()
               .baseUrl(String.format("%s/admin/realms/%s", keycloakServer, keycloakRealm))
               .build();
    }
}
