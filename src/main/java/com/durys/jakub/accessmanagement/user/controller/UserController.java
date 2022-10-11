package com.durys.jakub.accessmanagement.user.controller;

import com.durys.jakub.accessmanagement.user.model.dto.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(CreateUserRequest createUserRequest) {
        userService.create(createUserRequest);
    }

}
