package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.user.exception.UserNotFoundException;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user.model.util.AmUserDetails;
import com.durys.jakub.accessmanagement.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    public static AmUserDetails toAmUserDetails(User user) {
        return AmUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .isEnabled(!user.isLocked())
                .build();
    }
}
