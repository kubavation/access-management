package com.durys.jakub.accessmanagement.user.service;

import com.durys.jakub.accessmanagement.keycloak.KeycloakClientApi;
import com.durys.jakub.accessmanagement.shared.exception.EntityNotFoundException;
import com.durys.jakub.accessmanagement.user.exception.EmailAlreadyExistsException;
import com.durys.jakub.accessmanagement.user.exception.UserNotFoundException;
import com.durys.jakub.accessmanagement.user.exception.UsernameAlreadyExistsException;
import com.durys.jakub.accessmanagement.user.model.dto.creational.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

//    public boolean isUsernameAlreadyExists(String username) {
//        return userRepository.findByUsername(username).isPresent();
//    }
//
//    public boolean isEmailAlreadyExists(String email) {
//        return userRepository.findByUsername(email).isPresent();
//    }

}
