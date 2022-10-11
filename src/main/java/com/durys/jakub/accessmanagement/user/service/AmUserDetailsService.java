package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.user.model.util.AmUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AmUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public AmUserDetails loadUserByUsername(String username) {
        return UserService.toAmUserDetails(userService.findByUsername(username));
    }
}
