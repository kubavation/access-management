package com.durys.jakub.accessmanagement.user.infrastructure;

import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClient;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfiguration {

    @Bean
    UserRepository userRepository(KeycloakClient keycloakClient) {
        return new KeycloakUserRepository(keycloakClient);
    }
}
