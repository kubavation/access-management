package com.durys.jakub.accessmanagement.user.infrastructure;

import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClientService;
import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakUserRepository;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UsersConfiguration {

    @Bean
    UserRepository userRepository(KeycloakClientService keycloakClientService) {
        return new KeycloakUserRepository(keycloakClientService);
    }
}
