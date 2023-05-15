package com.durys.jakub.accessmanagement.role.domain;

import com.durys.jakub.accessmanagement.role.domain.exception.RoleWithNameAlreadyExistsException;

public class RoleValidator {

    private final RoleRepository roleRepository;

    public static RoleValidator instance(RoleRepository roleRepository) {
        return new RoleValidator(roleRepository);
    }

    private RoleValidator(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public void validateRoleExists(String role) {
        roleRepository.findById(role)
                .ifPresent(existingRole -> {
                    throw new RoleWithNameAlreadyExistsException(existingRole.getName());
                });
    }


}
