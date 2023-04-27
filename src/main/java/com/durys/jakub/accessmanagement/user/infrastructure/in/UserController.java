package com.durys.jakub.accessmanagement.user.infrastructure.in;

import com.durys.jakub.accessmanagement.shared.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import com.durys.jakub.accessmanagement.shared.mails.model.MailWithTemporaryPasswordDTO;
import com.durys.jakub.accessmanagement.shared.mails.service.MailSenderService;
import com.durys.jakub.accessmanagement.user.infrastructure.dto.UserDetailsDTO;
import com.durys.jakub.accessmanagement.user.infrastructure.dto.UserStatusDTO;
import com.durys.jakub.accessmanagement.user.infrastructure.dto.creational.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    //private final KeycloakClientApi keycloakClientApi;
    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.users();
    }

    @GetMapping("/{id}/details")
    public UserDetailsDTO getUserDetails(@PathVariable String id) {
       // return UserMapper.toDetailsDTO(keycloakClientApi.getUser(id));
    }

    @GetMapping("/{username}/exists")
    public boolean isUsernameAlreadyExists(@PathVariable String username) {
       return keycloakClientApi.isUserWithUsernameExists(username);
    }

    @PatchMapping("/{userId}/status")
    public void disableUser(@PathVariable String userId, @RequestBody UserStatusDTO userStatus) {

        if (Objects.isNull(userStatus)) {
            throw new IllegalArgumentException();
        }

        log.info("change user {} enabled-status to {}", userId, userStatus);

        keycloakClientApi.changeUserStatus(userId, userStatus.isEnabled());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        KeycloakUserCreatedResponse createdUserResponse = keycloakClientApi.createUser(createUserRequest);
        mailSenderService.send(MailWithTemporaryPasswordDTO.from(createdUserResponse.email(), createdUserResponse.password()));
    }

    @PutMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserRoles(@PathVariable String userId, @RequestBody List<RoleDTO> roles) {
        keycloakClientApi.addRolesToUser(userId, roles);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId) {
        keycloakClientApi.deleteUser(userId);
    }

    @GetMapping("/{id}/roles")
    public List<RoleDTO> getUserRoles(@PathVariable String id) {
        return keycloakClientApi.getUserRoles(id)
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }


}
