package com.durys.jakub.accessmanagement.role.controller;

import com.durys.jakub.accessmanagement.role.model.dto.CreateRoleRequest;
import com.durys.jakub.accessmanagement.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<CreateRoleRequest> findAll() {
        return roleService.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateRoleRequest role) {
        roleService.create(role);
    }
}
