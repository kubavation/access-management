package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.shared.exception.EntityNotFoundException;
import com.durys.jakub.accessmanagement.user.exception.UserNotFoundException;
import com.durys.jakub.accessmanagement.user.model.dto.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user.model.util.AmUserDetails;
import com.durys.jakub.accessmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));
    }


    public void create(CreateUserRequest request) {

        log.info("creating user {}", request);

        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setCreatedAt(LocalDateTime.now());
        user.setCreatedBy("todo-operator");
        user.setStatus("A");
        user.setLocked(false);

        userRepository.save(user);
    }



    public static AmUserDetails toAmUserDetails(User user) {
        return AmUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .isEnabled(!user.isLocked())
                .build();
    }


}
