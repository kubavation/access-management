package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.shared.exception.EntityNotFoundException;
import com.durys.jakub.accessmanagement.user.exception.EmailAlreadyExistsException;
import com.durys.jakub.accessmanagement.user.exception.UserNotFoundException;
import com.durys.jakub.accessmanagement.user.exception.UsernameAlreadyExistsException;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user.model.util.AmUserDetails;
import com.durys.jakub.accessmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    public User create(CreateUserRequest request) {

        log.info("creating user {}", request);

        if (isUsernameAlreadyExists(request.getUsername())) {
            throw new UsernameAlreadyExistsException(request.getUsername());
        }

        if (isEmailAlreadyExists(request.getEmail())) {
            throw new EmailAlreadyExistsException(request.getEmail());
        }

        return userRepository.save(prepareUserEntity(request));
    }

    private User prepareUserEntity(CreateUserRequest request) {
        return User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .status("A")
                .isLocked(false)
                .createdAt(LocalDateTime.now())
                .createdBy("todo-operator")
                .build();
    }


    public static AmUserDetails toAmUserDetails(User user) {
        return AmUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .isEnabled(!user.isLocked())
                .build();
    }

    public boolean isUsernameAlreadyExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    public boolean isEmailAlreadyExists(String email) {
        return userRepository.findByUsername(email).isPresent();
    }

}
