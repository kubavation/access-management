package com.durys.jakub.accessmanagement.keycloak;


import com.durys.jakub.accessmanagement.keycloak.KeycloakClientApi;
import lombok.NoArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.admin.client.resource.RealmResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class KeycloakExternalConfiguration {

    private final String keycloakServer;
    private final String keycloakRealm;
    private final String clientId;
    private final String secret;
    private final String externalLogin;
    private final String externalPassword;

    private final String mailServiceUrl;

    public KeycloakExternalConfiguration(@Value("${keycloak.auth-server-url}") String keycloakServer,
                                         @Value("${keycloak.realm}") String keycloakRealm,
                                         @Value("${keycloak.resource}")String clientId,
                                         @Value("${keycloak.credentials.secret}") String secret,
                                         @Value("${external-user.username}") String externalLogin,
                                         @Value("${external-user.password}") String externalPassword,
                                         @Value("${mail-service-url}") String mailServiceUrl) {

        this.keycloakServer = keycloakServer;
        this.keycloakRealm = keycloakRealm;
        this.clientId = clientId;
        this.secret = secret;
        this.externalLogin = externalLogin;
        this.externalPassword = externalPassword;
        this.mailServiceUrl = mailServiceUrl;
    }

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


    RealmResource realmResource() {
        return keycloakClient().realm(keycloakRealm);
    }

    @Bean
    WebClient.Builder webClientBuilder() {
        return WebClient.builder()
                .baseUrl(mailServiceUrl);
    }

    @Bean
    KeycloakClientService keycloakClientService(WebClient.Builder webClientBuilder) {
        return new KeycloakClientService(realmResource(), webClientBuilder);
    }

    @Bean
    KeycloakClientApi keycloakClientApi(KeycloakClientService keycloakClientService) {
        return new KeycloakClientApi(keycloakClientService);
    }

}
