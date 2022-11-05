package com.durys.jakub.accessmanagement.user.repository;

import com.durys.jakub.accessmanagement.user.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("from User u where u.status = 'A' and u.username = :username")
    Optional<User> findByUsername(String username);

    @Query("from User u where u.status = 'A' and u.email = :email")
    Optional<User> findByEmail(String email);
}
