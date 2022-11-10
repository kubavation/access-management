package com.durys.jakub.accessmanagement.role.repository;

import com.durys.jakub.accessmanagement.role.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    @Query("from Role r where r.status = 'A' order by r.name")
    List<Role> findAll();
    Optional<Role> findByName(String name);
}
