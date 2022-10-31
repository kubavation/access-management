package com.durys.jakub.accessmanagement.user.controller;

import com.durys.jakub.accessmanagement.user.mapper.UserMapper;
import com.durys.jakub.accessmanagement.user.model.dto.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.model.dto.UserDTO;
import com.durys.jakub.accessmanagement.user.model.entity.User;
import com.durys.jakub.accessmanagement.user.service.UserService;
import com.durys.jakub.accessmanagement.user_role.model.dto.AddRolesToUserRequest;
import com.durys.jakub.accessmanagement.user_role.service.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRoleService userRoleService;

    @GetMapping
    public List<UserDTO> getUsers() {
        return UserMapper.toDTO(userService.getUsers());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(CreateUserRequest createUserRequest) {
        userService.create(createUserRequest);
    }

    @PostMapping("/{id}/roles")
    @ResponseStatus(HttpStatus.CREATED)
    public void addRoles(@RequestBody AddRolesToUserRequest request) {
        User user = userService.findById(request.getUser().getId());
        userRoleService.addRolesToUser(request.getRoles(), user);
    }

}
