package com.durys.jakub.accessmanagement.user.controller;

import com.durys.jakub.accessmanagement.keycloak.KeycloakClientApi;
import com.durys.jakub.accessmanagement.keycloak.model.KeycloakUserCreatedResponse;
import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.mail_client.model.MailWithTemporaryPasswordDTO;
import com.durys.jakub.accessmanagement.user.mail_client.service.MailSenderService;
import com.durys.jakub.accessmanagement.user.mapper.UserMapper;
import com.durys.jakub.accessmanagement.user.model.dto.UserDetailsDTO;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final KeycloakClientApi keycloakClientApi;
    private final MailSenderService mailSenderService;


    @GetMapping
    public List<UserDTO> getUsers() {
        return UserMapper.toDTO(keycloakClientApi.getUsers());
    }

    @GetMapping("/{id}/details")
    public UserDetailsDTO getUserDetails(@PathVariable String id) {
        return UserMapper.toDetailsDTO(keycloakClientApi.getUser(id));
    }

    @GetMapping("/{username}/exists")
    public boolean isUsernameAlreadyExists(@PathVariable String username) {
       return keycloakClientApi.isUserWithUsernameExists(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        KeycloakUserCreatedResponse createdUserResponse = keycloakClientApi.createUser(createUserRequest);
        mailSenderService.send(MailWithTemporaryPasswordDTO.from(createdUserResponse.getEmail(), createdUserResponse.getPassword()));
    }

    @PutMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserRoles(@PathVariable String userId, @RequestBody List<RoleDTO> roles) {
        keycloakClientApi.addRolesToUser(userId, roles);
    }

    @GetMapping("/{id}/roles")
    public List<RoleDTO> getUserRoles(@PathVariable String id) {
        return keycloakClientApi.getUserRoles(id)
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }


}
