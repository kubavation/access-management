package com.durys.jakub.accessmanagement.user.application;

import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;

}
