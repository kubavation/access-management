package com.durys.jakub.accessmanagement.user.infrastructure;

import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClient;
import com.durys.jakub.accessmanagement.shared.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.infrastructure.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.infrastructure.dto.creational.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.List;

@RequiredArgsConstructor
public class KeycloakUserRepository implements UserRepository {

    private final KeycloakClient keycloakClient;

    public KeycloakUserCreatedResponse createUser(CreateUserRequest request) {
        return keycloakClient.createUser(request);
    }

    public List<RoleRepresentation> getUserRoles(String id) {
        return keycloakClient.userRoles(id);
    }

    public boolean isUserWithUsernameExists(String username) {
        return keycloakClient.usernameAlreadyExists(username);
    }

    public void changeUserStatus(String userId, boolean enabled) {
        keycloakClient.changeUserStatus(userId, enabled);
    }

    public void deleteUser(String userId) {
        keycloakClient.deleteUser(userId);
    }



    public List<RoleRepresentation> getRoles() {
        return keycloakClient.roles();
    }

    public void createRole(RoleDTO roleDTO) {
        keycloakClient.createRole(roleDTO);
    }

    public void updateRole(String roleName, RoleDTO roleDTO) {
        keycloakClient.updateRole(roleName, roleDTO);
    }

    public void deleteRole(String roleName) {
        keycloakClient.deleteRole(roleName);
    }

    public boolean hasRole(String userId, String role) {
        return getUserRoles(userId)
                .stream()
                .map(RoleRepresentation::getName)
                .anyMatch(r -> r.equals(role));
    }

    public List<UserRepresentation> getUsersWithRole(String role) {
        return keycloakClient.usersWithRole(role);
    }

    @Override
    public List<User> users() {
        return KeycloakUserConverter.instance()
                .toUsers(keycloakClient.users());
    }

    @Override
    public User userById(String id) {
        return KeycloakUserConverter.instance()
                .toUser(keycloakClient.userById(id));
    }

    @Override
    public void delete(User user) {
        keycloakClient.deleteUser(user.getId());
    }
}
