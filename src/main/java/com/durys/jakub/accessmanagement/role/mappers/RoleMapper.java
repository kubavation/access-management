package com.durys.jakub.accessmanagement.role.mappers;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.role.repository.RoleRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMapper {

    public static Role toEntity(RoleDTO dto) {
        return new Role(dto.getName(), dto.getDescription());
    }

    public static RoleDTO toDTO(Role entity) {
        return new RoleDTO(entity.getName(), entity.getDesc());
    }

    public static RoleDTO toDTO(RoleRepresentation role) {
        return new RoleDTO(role.getName(), role.getDescription());
    }

    public static List<RoleDTO> toDTO(List<RoleRepresentation> roles) {
        return roles.stream()
                .map(RoleMapper::toDTO)
                .toList();
    }
}
