package com.durys.jakub.accessmanagement.role.infrastructure.in;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.role.infrastructure.model.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @GetMapping
    public List<Role> findAll() {
        return roleRepository.roles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RoleDTO role) {
        roleRepository.save(new Role(role.getName(), role.getDescription()));
    }

    @PutMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String name, @RequestBody RoleDTO role) {
        roleRepository.save(new Role(name, role.getDescription()));
    }

    @DeleteMapping("/{name}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {
        Role role = roleRepository.findById(name)
                .orElseThrow(() -> new RuntimeException("todo"));

        roleRepository.delete(role);
    }

}
