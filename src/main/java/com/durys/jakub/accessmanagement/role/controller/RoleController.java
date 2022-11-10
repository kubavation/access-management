package com.durys.jakub.accessmanagement.role.controller;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.role.service.RoleService;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user_role.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.user_role.model.dto.AddUsersToRoleRequest;
import com.durys.jakub.accessmanagement.user_role.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;
    private final UserRoleService userRoleService;

    @GetMapping
    public List<RoleDTO> findAll() {
        return roleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RoleDTO role) {
        roleService.create(role);
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String name, @RequestBody RoleDTO role) {
        roleService.update(name, role);
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {
        roleService.delete(name);
    }

    @PostMapping("/{name}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoles(@RequestBody AddUsersToRoleRequest request) {
        Role role = roleService.findByName(request.getRole().getName());
        userRoleService.addUsersToRole(request.getUsers(), role);
    }

}
