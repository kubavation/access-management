package com.durys.jakub.accessmanagement.api;

import com.durys.jakub.accessmanagement.keycloak.KeycloakUserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final KeycloakUserRepository keycloakUserRepository;

    @GetMapping("/{userId}")
    UserRepresentation user(@PathVariable String userId) {
        return keycloakUserRepository.getUser(userId);
    }

    @GetMapping("/{userId}/roles")
    List<RoleRepresentation> userRoles(@PathVariable String userId) {
        return keycloakUserRepository.getUserRoles(userId);
    }

    @GetMapping("/{userId}/roles/contains/{role}")
    boolean hasRole(@PathVariable String userId, @PathVariable String role) {
        return keycloakUserRepository.hasRole(userId, role);
    }

    @GetMapping("/roles/{role}")
    List<UserRepresentation> usersWithRole(@PathVariable String role) {
        return keycloakUserRepository.getUsersWithRole(role);
    }

}
