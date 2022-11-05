package com.durys.jakub.accessmanagement.user.model.dto.creational;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@NoArgsConstructor
@Data
public class CreateUserRequest {

    private String username;
    @ToString.Exclude
    private String password;
    private String email;
    private String firstName;
    private String lastName;
    private List<RoleDTO> roles;

}
