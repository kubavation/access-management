package com.durys.jakub.accessmanagement.user.application;

import com.durys.jakub.accessmanagement.ddd.annotation.ApplicationService;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import com.durys.jakub.accessmanagement.user.domain.UserValidator;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    
    public void create(String username, String email) {

        User user = new User(UUID.randomUUID().toString(), username, email);

        Either<UserValidator.ValidationError, Void> validationResult = UserValidator
                .instance(userRepository)
                .validateUser(user);

        if (validationResult.isLeft()) {
            //todo handle validationResult.getLeft()
        }

        userRepository.save(user);
    }

}
