package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@RequiredArgsConstructor
public class KeycloakClientApi {

    private final KeycloakClientService keycloakClientService;

    public List<UserRepresentation> getUsers() {
        return keycloakClientService.getUsers();
    }

    public KeycloakUserCreatedResponse createUser(CreateUserRequest request) {
        return keycloakClientService.createUser(request);
    }

    public UserRepresentation getUser(String id) {
        return keycloakClientService.getUser(id);
    }

    public void addRolesToUser(String userId, List<RoleDTO> roles) {
        keycloakClientService.updateUserRoles(userId, roles);
    }

    public List<RoleRepresentation> getUserRoles(String id) {
        return keycloakClientService.getUserRoles(id);
    }

    public boolean isUserWithUsernameExists(String username) {
        return keycloakClientService.isUserWithUsernameExists(username);
    }

    public void changeUserStatus(String userId, boolean enabled) {
        keycloakClientService.changeUserStatus(userId, enabled);
    }

    public void deleteUser(String userId) {
        keycloakClientService.deleteUser(userId);
    }



    public List<RoleRepresentation> getRoles() {
        return keycloakClientService.getRoles();
    }

    public void createRole(RoleDTO roleDTO) {
        keycloakClientService.createRole(roleDTO);
    }

    public void updateRole(String roleName, RoleDTO roleDTO) {
        keycloakClientService.updateRole(roleName, roleDTO);
    }

    public void deleteRole(String roleName) {
        keycloakClientService.deleteRole(roleName);
    }

    public boolean hasRole(String userId, String role) {
        return getUserRoles(userId)
                .stream()
                .map(RoleRepresentation::getName)
                .anyMatch(r -> r.equals(role));
    }

}
