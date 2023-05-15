package com.durys.jakub.accessmanagement.role.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;

@AllArgsConstructor
@Data
public class Role {
    private String name;
    private String description;
}
