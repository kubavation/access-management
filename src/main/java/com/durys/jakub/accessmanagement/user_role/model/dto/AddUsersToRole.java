package com.durys.jakub.accessmanagement.user_role.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class AddUsersToRole {
    private Long roleName;
    private List<Long> userIds;
}
