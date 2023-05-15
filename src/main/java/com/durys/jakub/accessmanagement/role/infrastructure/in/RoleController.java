package com.durys.jakub.accessmanagement.role.infrastructure.in;

import com.durys.jakub.accessmanagement.role.application.RoleApplicationService;
import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.role.infrastructure.model.RoleDTO;
import com.durys.jakub.accessmanagement.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;
    private final RoleApplicationService roleApplicationService;

    @GetMapping
    public List<Role> findAll() {
        return roleRepository.roles();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody RoleDTO dto) {
        roleApplicationService.create(dto.getName(), dto.getDescription());
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody RoleDTO dto) {

        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, id));

        role.setName(dto.getName());
        role.setDescription(dto.getDescription());

        roleRepository.save(role);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, id));
        roleRepository.delete(role);
    }

}
