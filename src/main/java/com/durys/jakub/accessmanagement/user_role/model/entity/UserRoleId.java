package com.durys.jakub.accessmanagement.user_role.model.entity;

import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;

@Embeddable
@Getter
public class UserRoleId implements Serializable {

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne
    @JoinColumn(name = "role_name", referencedColumnName = "name")
    private Role role;
}
