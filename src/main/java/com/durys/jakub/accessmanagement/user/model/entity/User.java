package com.durys.jakub.accessmanagement.user.model.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "AM_USER")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
