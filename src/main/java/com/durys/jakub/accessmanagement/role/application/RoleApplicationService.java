package com.durys.jakub.accessmanagement.role.application;

import com.durys.jakub.accessmanagement.ddd.annotation.ApplicationService;
import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.role.domain.exception.RoleWithNameAlreadyExistsException;
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

}
