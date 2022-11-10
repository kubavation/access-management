package com.durys.jakub.accessmanagement.user_role.model.entity;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "AM_USER_ROLE")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRole {

    @Id
    private UserRoleId id;

    public UserRole(User user, Role role) {
        this.id = new UserRoleId(user, role);
    }
}
