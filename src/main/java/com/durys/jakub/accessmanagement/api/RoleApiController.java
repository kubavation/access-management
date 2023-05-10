package com.durys.jakub.accessmanagement.api;

import com.durys.jakub.accessmanagement.role.domain.RoleRepository;
import com.durys.jakub.accessmanagement.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@RequiredArgsConstructor
public class RoleApiController {

    private final RoleRepository roleRepository;

    @GetMapping("/{role}/users")
    List<User> usersWithRole(@PathVariable String role) {
        return roleRepository.usersWithRole(role);
    }

}
