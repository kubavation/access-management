package com.durys.jakub.accessmanagement.user.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "AM_USER")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class User {

    @Id
    private Long id;

    private String username;

    @ToString.Exclude
    private String password;

    @Column(name = "employee_id")
    private Long employeeId;

    @Column(name = "is_locked")
    private boolean isLocked;

    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "created_by")
    private String createdBy;

}
