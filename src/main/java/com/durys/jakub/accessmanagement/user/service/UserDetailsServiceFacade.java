package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.role.model.dto.RoleDTO;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user_role.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.user_role.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailsServiceFacade {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @Transactional
    public void createUser(CreateUserRequest createUserRequest) {
        log.info("START | request {}", createUserRequest);
        
        User user = userService.create(createUserRequest);
        addRolesToUser(createUserRequest.getRoles(), user);
    }

    public void addRolesToUser(List<RoleDTO> roles, User user) {
        userRoleService.addRolesToUser(roles, user);
    }

    @Transactional
    public void addRolesToUser(AddRolesToUserRequest request) {
        User user = userService.findById(request.getUser().getId());
        addRolesToUser(request.getRoles(), user);
    }
}
