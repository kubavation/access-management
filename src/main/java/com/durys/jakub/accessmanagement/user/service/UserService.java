package com.durys.jakub.accessmanagement.user.service;

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
                .orElseThrow(RuntimeException::new); //todo
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(RuntimeException::new); //todo
    }

    public static AmUserDetails toAmUserDetails(User user) {
        return AmUserDetails.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .isEnabled(!user.isLocked()) //todo
                .build();
    }
}
