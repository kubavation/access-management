package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.UserRepository;
import com.durys.jakub.accessmanagement.user.model.dto.User;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@RequiredArgsConstructor
public class KeycloakClientApi implements UserRepository {

    private final KeycloakClientService keycloakClientService;

    public List<UserRepresentation> getUsers() {
        return keycloakClientService.users();
    }

    public KeycloakUserCreatedResponse createUser(CreateUserRequest request) {
        return keycloakClientService.createUser(request);
    }

    public UserRepresentation getUser(String id) {
        return keycloakClientService.userById(id);
    }

    public void addRolesToUser(String userId, List<RoleDTO> roles) {
        keycloakClientService.updateUserRoles(userId, roles);
    }

    public List<RoleRepresentation> getUserRoles(String id) {
        return keycloakClientService.userRoles(id);
    }

    public boolean isUserWithUsernameExists(String username) {
        return keycloakClientService.usernameAlreadyExists(username);
    }

    public void changeUserStatus(String userId, boolean enabled) {
        keycloakClientService.changeUserStatus(userId, enabled);
    }

    public void deleteUser(String userId) {
        keycloakClientService.deleteUser(userId);
    }



    public List<RoleRepresentation> getRoles() {
        return keycloakClientService.roles();
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

    public List<UserRepresentation> getUsersWithRole(String role) {
        return keycloakClientService.usersWithRole(role);
    }

    @Override
    public List<User> users() {
        return KeycloakUserConverter.instance().toUsers(keycloakClientService.users());
    }

    @Override
    public User userById(String id) {
        return KeycloakUserConverter.instance().toUser(keycloakClientService.userById(id));
    }
}
