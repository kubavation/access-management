package com.durys.jakub.accessmanagement.user.domain;

import com.durys.jakub.accessmanagement.ddd.annotation.DomainService;
import lombok.RequiredArgsConstructor;

@DomainService
@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public boolean usernameAlreadyExists(String username) {
        return userRepository.users()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    public boolean emailAlreadyExists(String email) {
        return userRepository.users()
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

}
