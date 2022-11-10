package com.durys.jakub.accessmanagement.role.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "AM_ROLE")
@Entity
public class Role {

    @Id
    private String name;
    @Column(name = "description")
    private String desc;

    @Column(name = "CREATED_AT")
    private LocalDateTime createdAt;

    private String status;

    public Role(String name, String desc) {
        this.name = name;
        this.desc = desc;
    }
}
