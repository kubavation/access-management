package com.durys.jakub.accessmanagement.role.controller;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;

    @GetMapping
    public List<RoleDTO> findAll() {
        return roleService.findAll();
    }

    @PostMapping
    public void create(@RequestBody RoleDTO roleDTO) {
        roleService.create(roleDTO);
    }
}
