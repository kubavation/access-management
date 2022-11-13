package com.durys.jakub.accessmanagement.role.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AddRolesToUserRequest {
    private String userId;
    private List<RoleDTO> roles;
}
