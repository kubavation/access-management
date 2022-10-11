package com.durys.jakub.accessmanagement.role.mappers;

import com.durys.jakub.accessmanagement.role.model.dto.CreateRoleRequest;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMapper {

    public static Role toEntity(CreateRoleRequest dto) {
        return new Role(dto.getName(), dto.getDesc());
    }

    public static CreateRoleRequest toDTO(Role entity) {
        return new CreateRoleRequest(entity.getName(), entity.getDesc());
    }

    public static List<CreateRoleRequest> toDTO(List<Role> entities) {
        return entities.stream()
                .map(RoleMapper::toDTO)
                .toList();
    }
}
