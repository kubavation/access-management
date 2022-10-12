package com.durys.jakub.accessmanagement.role.mappers;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMapper {

    public static Role toEntity(RoleDTO dto) {
        return new Role(dto.getName(), dto.getDesc());
    }

    public static RoleDTO toDTO(Role entity) {
        return new RoleDTO(entity.getName(), entity.getDesc());
    }

    public static List<RoleDTO> toDTO(List<Role> entities) {
        return entities.stream()
                .map(RoleMapper::toDTO)
                .toList();
    }
}
