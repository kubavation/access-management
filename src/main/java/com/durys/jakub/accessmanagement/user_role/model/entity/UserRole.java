package com.durys.jakub.accessmanagement.user_role.model.entity;

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
}
