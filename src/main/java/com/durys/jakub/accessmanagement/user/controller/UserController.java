package com.durys.jakub.accessmanagement.user.controller;

import com.durys.jakub.accessmanagement.keycloak.KeycloakClientApi;
import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.mapper.UserMapper;
import com.durys.jakub.accessmanagement.user.model.dto.UserDetailsDTO;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.service.UserDetailsServiceFacade;
import com.durys.jakub.accessmanagement.user.service.UserService;
import com.durys.jakub.accessmanagement.user_role.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.user_role.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDetailsServiceFacade userDetailsServiceFacade;

    private final KeycloakClientApi keycloakClientApi;


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
        return userService.isUsernameAlreadyExists(username);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest createUserRequest) {
        userDetailsServiceFacade.createUser(createUserRequest);
    }

    @PostMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoles(@RequestBody AddRolesToUserRequest request) {
        userDetailsServiceFacade.addRolesToUser(request);
    }

    @GetMapping("/{id}/roles")
    public List<RoleDTO> getUserRoles(@PathVariable String id) {
        return keycloakClientApi.getUserRoles(id)
                .stream()
                .map(RoleMapper::toDTO)
                .toList();
    }


}
