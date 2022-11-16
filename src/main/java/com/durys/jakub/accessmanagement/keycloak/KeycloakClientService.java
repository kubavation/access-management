package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.exception.UsernameAlreadyExistsException;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;

import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


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

    public Boolean isUserWithUsernameExists(String username) {
        return realmResource.users().search(username)
                .stream().findFirst().isPresent();
    }


    public List<RoleRepresentation> getUserRoles(String userId) {
        return realmResource.users().get(userId).roles().realmLevel().listAll();
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
        UserRepresentation userRepresentation = realmResource.users().get(userId).toRepresentation();
        userRepresentation.setRealmRoles(KeycloakUtils.toRealmRoles(roles));
        realmResource.users().get(userId).update(userRepresentation);
    }


    @Transactional
    public KeycloakUserCreatedResponse createUser(CreateUserRequest createUserRequest) {

        UserRepresentation userRepresentation = KeycloakUtils.toKeycloakUserRepresentation(createUserRequest);

        CredentialRepresentation credentialRepresentation = KeycloakPasswordGenerator.credentialRepresentation();
        userRepresentation.setCredentials(Collections.singletonList(credentialRepresentation));

        var response = realmResource.users().create(userRepresentation);

        validateResponseAfterUserCreation(response, userRepresentation);

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

}
