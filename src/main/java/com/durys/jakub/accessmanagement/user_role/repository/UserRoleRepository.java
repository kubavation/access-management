package com.durys.jakub.accessmanagement.user_role.repository;

import com.durys.jakub.accessmanagement.role.model.entity.Role;
import com.durys.jakub.accessmanagement.user_role.model.entity.UserRole;
import com.durys.jakub.accessmanagement.user_role.model.entity.UserRoleId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleId> {

    @Query("FROM UserRole ur WHERE ur.id.user.id = :userId")
    List<UserRole> findAllByUserId(Long userId);

    @Query("FROM UserRole ur WHERE ur.id.role.name = :roleName")
    List<UserRole> findAllByRoleName(String roleName);
}
