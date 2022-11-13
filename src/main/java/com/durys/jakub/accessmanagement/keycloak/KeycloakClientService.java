package com.durys.jakub.accessmanagement.keycloak;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.resource.RealmResource;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
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

    public List<RoleRepresentation> getUserRoles(String userId) {
        return realmResource.users().get(userId).roles().realmLevel()
                .listAll()
                .stream()
                .toList();
    }

    public List<RoleRepresentation> getRoles() {
        return realmResource.roles().list();
    }

    public void createRole(RoleDTO roleDTO) {
        RoleRepresentation roleRepresentation
                = new RoleRepresentation(roleDTO.getName(), roleDTO.getDescription(), false);
        realmResource.roles().create(roleRepresentation);
    }

    public void updateRole(String roleName, RoleDTO roleDTO) {
        RoleRepresentation roleRepresentation
                = new RoleRepresentation(roleName, roleDTO.getDescription(), false);
        realmResource.roles().get(roleName).update(roleRepresentation);
    }
}
