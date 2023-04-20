package com.durys.jakub.accessmanagement.api;

import com.durys.jakub.accessmanagement.keycloak.KeycloakClientApi;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final KeycloakClientApi keycloakClientApi;

    @GetMapping("/{userId}/roles")
    List<RoleRepresentation> userRoles(@PathVariable String userId) {
        return keycloakClientApi.getUserRoles(userId);
    }

    @GetMapping("/{userId}/roles/{role}")
    boolean hasRole(@PathVariable String userId, @PathVariable String role) {
        return keycloakClientApi.hasRole(userId, role);
    }

}
