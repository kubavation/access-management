package com.durys.jakub.accessmanagement.user.infrastructure.in;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.user.application.UserApplicationService;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import com.durys.jakub.accessmanagement.user.domain.UserValidator;
import com.durys.jakub.accessmanagement.user.infrastructure.model.CreateUserRequest;
import com.durys.jakub.accessmanagement.user.infrastructure.model.UserDetailsDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserApplicationService userApplicationService;

    @GetMapping
    public List<User> getUsers() {
        return userRepository.users();
    }

    @GetMapping("/{id}/details")
    public UserDetailsDTO getUserDetails(@PathVariable String id) {
       // return UserMapper.toDetailsDTO(keycloakClientApi.getUser(id));
        return null;
    }

    @GetMapping("/{username}/exists")
    public boolean isUsernameAlreadyExists(@PathVariable String username) {
       return UserValidator.instance(userRepository)
               .usernameAlreadyExists(username);
    }

    @PatchMapping("/{userId}/status/disabled")
    public void disableUser(@PathVariable String userId) {
        userApplicationService.disable(userId);
    }

    @PatchMapping("/{userId}/status/enabled")
    public void enableUser(@PathVariable String userId) {
        userApplicationService.enable(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody CreateUserRequest req) {
        userApplicationService.create(req.getUsername(), req.getEmail());
    }

    @PutMapping("/{userId}/roles")
    @ResponseStatus(HttpStatus.OK)
    public void updateUserRoles(@PathVariable String userId, @RequestBody List<Role> roles) {
        userApplicationService.setRoles(userId, roles);
    }

    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@PathVariable String userId) {
        userApplicationService.delete(userId);
    }

    @GetMapping("/{id}/roles")
    public List<Role> getUserRoles(@PathVariable String id) {
        return userRepository.userRoles(id);
    }


}
