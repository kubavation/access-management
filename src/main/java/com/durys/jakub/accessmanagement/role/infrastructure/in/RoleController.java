package com.durys.jakub.accessmanagement.role.infrastructure.in;

import com.durys.jakub.accessmanagement.user.infrastructure.KeycloakUserRepository;
import com.durys.jakub.accessmanagement.role.mappers.RoleMapper;
import com.durys.jakub.accessmanagement.role.infrastructure.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.role.infrastructure.model.dto.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final KeycloakUserRepository keycloakUserRepository;

    @GetMapping
    public List<RoleDTO> findAll() {
        return RoleMapper.toDTO(keycloakUserRepository.getRoles());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RoleDTO role) {
        keycloakUserRepository.createRole(role);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String name, @RequestBody RoleDTO role) {
        keycloakUserRepository.updateRole(name, role);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {
        keycloakUserRepository.deleteRole(name);
    }

    @PostMapping("/{name}/roles")
    @ResponseStatus(HttpStatus.OK)
    public void addRoles(@RequestBody AddRolesToUserRequest request) {
        keycloakUserRepository.addRolesToUser(request.getUserId(), request.getRoles());
    }

}
