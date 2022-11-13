package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class KeycloakClientApi {

    private final KeycloakClientService keycloakClientService;

    public List<UserRepresentation> getUsers() {
        return keycloakClientService.getUsers();
    }

    public UserRepresentation getUser(String id) {
        return keycloakClientService.getUser(id);
    }

    public List<RoleRepresentation> getRoles() {
        return keycloakClientService.getRoles();
    }

    public void createRole(RoleDTO roleDTO) {
        keycloakClientService.createRole(roleDTO);
    }

    public List<RoleRepresentation> getUserRoles(String id) {
        return keycloakClientService.getUserRoles(id);
    }
}
