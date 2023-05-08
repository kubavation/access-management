package com.durys.jakub.accessmanagement.role.infrastructure;

import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfiguration {

    @Bean
    RoleRepository roleRepository(KeycloakClient keycloakClient) {
        return new KeycloakRoleRepository(keycloakClient);
    }
}
