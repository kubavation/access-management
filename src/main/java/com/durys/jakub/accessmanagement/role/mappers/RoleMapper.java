package com.durys.jakub.accessmanagement.role.mappers;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.keycloak.representations.idm.RoleRepresentation;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RoleMapper {

    public static RoleDTO toDTO(RoleRepresentation role) {
        return new RoleDTO(role.getName(), role.getDescription());
    }

    public static List<RoleDTO> toDTO(List<RoleRepresentation> roles) {
        return roles.stream()
                .map(RoleMapper::toDTO)
                .toList();
    }
}
