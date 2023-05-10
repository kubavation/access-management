package com.durys.jakub.accessmanagement.api;

import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserRepository userRepository;

    @GetMapping("/{userId}")
    User user(@PathVariable String userId) {
        return userRepository.findById(userId)
                .orElseThrow(RuntimeException::new); //todo
    }

    @GetMapping("/{userId}/roles")
    List<Role> userRoles(@PathVariable String userId) {
        return userRepository.userRoles(userId);
    }

    @GetMapping("/{userId}/roles/contains/{role}")
    boolean hasRole(@PathVariable String userId, @PathVariable String role) {
        return userRepository.userRoles(userId)
                .stream()
                .map(Role::name)
                .toList()
                .contains(role);
    }


}
