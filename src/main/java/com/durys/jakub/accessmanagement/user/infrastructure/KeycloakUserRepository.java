package com.durys.jakub.accessmanagement.user.infrastructure;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.infrastructure.KeycloakRoleConverter;
import com.durys.jakub.accessmanagement.shared.keycloak.KeycloakClient;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.keycloak.representations.idm.UserRepresentation;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class KeycloakUserRepository implements UserRepository {

    private final KeycloakClient keycloakClient;

    @Override
    public List<User> users() {
        return KeycloakUserConverter.instance()
                .toUsers(keycloakClient.users());
    }

    @Override
    public Optional<User> findById(String id) {
        return keycloakClient.users()
                .stream()
                .filter(user -> user.getId().equals(id))
                .map(user -> KeycloakUserConverter.instance().toUser(user))
                .findFirst();
    }

    @Override
    public void save(User user) {
        UserRepresentation representation = KeycloakUserConverter.instance().toRepresentation(user);
        keycloakClient.users().add(representation);
    }

    @Override
    public void delete(User user) {
        UserRepresentation representation = KeycloakUserConverter.instance().toRepresentation(user);
        keycloakClient.users().remove(representation);
    }

    @Override
    public List<Role> userRoles(String id) {
        return keycloakClient.userById(id)
                    .map(UserRepresentation::getRealmRoles)
                    .map(KeycloakClient::roleNamesToRepresentations)
                    .map(KeycloakRoleConverter.instance()::toRoles)
                    .orElse(Collections.emptyList());
    }

    @Override
    public void setRoles(String userId, List<Role> roles) {

        List<String> roleNames = roles.stream()
                .map(Role::getName)
                .toList();

        keycloakClient.userById(userId)
                .ifPresent(user -> user.setRealmRoles(roleNames));
    }
}
