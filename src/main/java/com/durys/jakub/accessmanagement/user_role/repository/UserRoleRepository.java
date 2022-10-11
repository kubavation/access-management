package com.durys.jakub.accessmanagement.user_role.repository;

import com.durys.jakub.accessmanagement.user_role.model.entity.UserRole;
import com.durys.jakub.accessmanagement.user_role.model.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {
}
