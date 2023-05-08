package com.durys.jakub.accessmanagement.role.infrastructure;

import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClientService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RolesConfiguration {

    @Bean
    RoleRepository roleRepository(KeycloakClientService keycloakClientService) {
        return new KeycloakRoleRepository(keycloakClientService);
    }
}
