package com.durys.jakub.accessmanagement.user_role.model.dto;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AddRolesToUserRequest {
    private UserDTO user;
    private List<RoleDTO> roles;
}