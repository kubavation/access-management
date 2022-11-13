package com.durys.jakub.accessmanagement.role.controller;

import com.durys.jakub.accessmanagement.keycloak.KeycloakClientApi;
import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final KeycloakClientApi keycloakClientApi;

    @GetMapping
    public List<RoleDTO> findAll() {
        return RoleMapper.toDTO(keycloakClientApi.getRoles());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RoleDTO role) {
        keycloakClientApi.createRole(role);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String name, @RequestBody RoleDTO role) {
        keycloakClientApi.updateRole(name, role);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {
        keycloakClientApi.deleteRole(name);
    }

    @PostMapping("/{name}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoles(@RequestBody AddRolesToUserRequest request) {
        keycloakClientApi.addRolesToUser(request.getUserId(), request.getRoles());
    }

}
