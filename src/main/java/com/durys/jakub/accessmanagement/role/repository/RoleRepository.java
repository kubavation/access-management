package com.durys.jakub.accessmanagement.role.repository;

import com.durys.jakub.accessmanagement.role.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
