package com.durys.jakub.accessmanagement.role.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateRoleRequest {
    private String name;
    private String desc;
}
