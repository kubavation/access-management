package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.user.model.dto.CreateUserRequest;
import com.durys.jakub.accessmanagement.user_role.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceFacade {

    private final UserService userService;
    private final UserRoleService userRoleService;

    public void createUser(CreateUserRequest createUserRequest) {
        log.info("START | request {}", createUserRequest);
    }
}
