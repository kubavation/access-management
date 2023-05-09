package com.durys.jakub.accessmanagement.user.application;

import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;

    public void create(String username, String email) {
        User user = new User(UUID.randomUUID().toString(), username, email);
        userRepository.save(user);
    }

}
