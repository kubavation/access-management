package com.durys.jakub.accessmanagement.authentication.service;

import com.durys.jakub.accessmanagement.authentication.model.AuthenticationRequest;
import com.durys.jakub.accessmanagement.authentication.model.AuthenticationResponse;
import com.durys.jakub.accessmanagement.security.util.JwtSecurityUtils;
import com.durys.jakub.accessmanagement.user.exception.BadCredentialsException;
import com.durys.jakub.accessmanagement.user.exception.UserLockedException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtSecurityUtils jwtSecurityUtils;

    public AuthenticationResponse authenticate(AuthenticationRequest auth) {

        log.info("authentication - start with username {}", auth.getUsername());

        try {

            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth.getUsername(), auth.getPassword()));

        } catch (AuthenticationException ex) {
            log.error("error in authentication", ex);
            throw new BadCredentialsException();
        }

        UserDetails user = userDetailsService.loadUserByUsername(auth.getUsername());

        if (!user.isEnabled()) {
            throw new UserLockedException(auth.getUsername());
        }

        String token = jwtSecurityUtils.generateToken(user.getUsername());

        System.out.println(token);

        log.info("authentication - successful with user {}", auth.getUsername());

        return new AuthenticationResponse(token);
    }
}
