package com.durys.jakub.accessmanagement.role.application;

import com.durys.jakub.accessmanagement.ddd.annotation.ApplicationService;
import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.role.domain.exception.RoleWithNameAlreadyExistsException;
import com.durys.jakub.accessmanagement.shared.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@ApplicationService
@RequiredArgsConstructor
public class RoleApplicationService {

    private final RoleRepository roleRepository;

    public void create(String name, String description) {

        roleRepository.findById(name)
                .ifPresent(role -> {
                    throw new RoleWithNameAlreadyExistsException(role.getName());
                });

        Role role = new Role(name, description);
        roleRepository.save(role);
    }

    public void update(String existingName, String name, String description) {

        Role role = roleRepository.findById(existingName)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, existingName));

        roleRepository.findById(name)
                .ifPresent(existingRole -> {
                    throw new RoleWithNameAlreadyExistsException(existingRole.getName());
                });

        role.setName(name);
        role.setDescription(description);

        roleRepository.save(role);
    }

    public void delete(String id) {
        Role role = roleRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(Role.class, id));
        roleRepository.delete(role);
    }

}
