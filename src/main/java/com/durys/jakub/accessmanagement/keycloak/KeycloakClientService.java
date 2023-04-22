package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.exception.UsernameAlreadyExistsException;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.RoleResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import jakarta.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.*;


@RequiredArgsConstructor
class KeycloakClientService {

    private final RealmResource realmResource;

    public List<UserRepresentation> getUsers() {
        return realmResource.users().list();
    }

    public UserRepresentation getUser(String id) {
        return Optional.ofNullable(realmResource.users().get(id))
                .map(UserResource::toRepresentation)
                .orElseThrow(() -> new RuntimeException("user not found"));
    }


    public UserRepresentation getUserByUsername(String username) {
        return realmResource.users().search(username)
                .stream().findFirst()
                .orElseThrow(() -> new RuntimeException("user not found"));
    }

    public List<UserRepresentation> getUsersWithRole(String role) {
        return realmResource.users().list()
                .stream()
                .filter(u -> u.getRealmRoles().contains(role))
                .toList();
    }

    public Boolean isUserWithUsernameExists(String username) {
        return realmResource.users().search(username)
                .stream().findFirst().isPresent();
    }


    public List<RoleRepresentation> getUserRoles(String userId) {
        return realmResource.users().get(userId).roles().getAll().getRealmMappings();
    }

    public List<RoleRepresentation> getRoles() {
        return realmResource.roles().list();
    }

    public void createRole(RoleDTO roleDTO) {
       realmResource.roles()
               .create(KeycloakUtils.toKeycloakRoleRepresentation(roleDTO));
    }

    public void updateRole(String roleName, RoleDTO roleDTO) {
        roleDTO.setName(roleName);
        realmResource.roles().get(roleName).update(KeycloakUtils.toKeycloakRoleRepresentation(roleDTO));
    }

    public void deleteRole(String roleName) {
        realmResource.roles().get(roleName).remove();
    }

    public void addRolesToUser(String userId, List<RoleDTO> roles) {

        UserResource userResource = realmResource.users().get(userId);

        List<RoleRepresentation> rolesRepresentations = roles.stream()
                .map(role -> realmResource.roles().get(role.getName()))
                .filter(Objects::nonNull)
                .map(RoleResource::toRepresentation).toList();

        userResource.roles().realmLevel().add(rolesRepresentations);
    }

    private void clearUserRoles(String userId) {

        List<RoleRepresentation> existingUserRoles = realmResource.users().get(userId)
                .roles().realmLevel().listAll();

        realmResource.users().get(userId)
                .roles().realmLevel()
                .remove(existingUserRoles);
    }

    @Transactional
    public void updateUserRoles(String userId, List<RoleDTO> roles) {
        clearUserRoles(userId);
        addRolesToUser(userId, roles);
    }


    @Transactional
    public KeycloakUserCreatedResponse createUser(CreateUserRequest createUserRequest) {

        UserRepresentation userRepresentation = KeycloakUtils.toKeycloakUserRepresentation(createUserRequest);

        CredentialRepresentation credentialRepresentation = KeycloakPasswordGenerator.credentialRepresentation();
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        var response = realmResource.users().create(userRepresentation);

        validateResponseAfterUserCreation(response, userRepresentation);

        addRolesToUser(CreatedResponseUtil.getCreatedId(response), createUserRequest.getRoles());


        return new KeycloakUserCreatedResponse(CreatedResponseUtil.getCreatedId(response),
                credentialRepresentation.getValue(), createUserRequest.getEmail());
    }

    private void validateResponseAfterUserCreation(Response response, UserRepresentation userRepresentation) {
        switch (response.getStatus()) {
            case 201:
                return;
            case 409:
                throw new UsernameAlreadyExistsException(userRepresentation.getUsername());
            default:
                throw new RuntimeException("Failed to create user!");
        }
    }

    public void changeUserStatus(String userId, boolean enabled) {
        UserRepresentation user = getUser(userId);
        user.setEnabled(enabled);
        realmResource.users().get(userId).update(user);
    }

    public void deleteUser(String userId) {
        realmResource.users().get(userId).remove();
    }
}
