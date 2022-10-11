package com.durys.jakub.accessmanagement.user.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
public class CreateUserRequest {

    private String username;

    @ToString.Exclude
    private String password;

}
