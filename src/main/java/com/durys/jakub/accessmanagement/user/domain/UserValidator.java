package com.durys.jakub.accessmanagement.user.domain;

import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserValidator {

    private final UserRepository userRepository;

    public enum ValidationError {
        USERNAME_ALREADY_EXISTS,
        EMAIL_ALREADY_EXISTS
    }

    public static UserValidator instance(UserRepository userRepository) {
        return new UserValidator(userRepository);
    }

    public Either<ValidationError, Void> validateUser(User user) {
        if (usernameAlreadyExists(user.getUsername())) {
            return Either.left(ValidationError.USERNAME_ALREADY_EXISTS);
        }
        if (emailAlreadyExists(user.getEmail())) {
            return Either.left(ValidationError.EMAIL_ALREADY_EXISTS);
        }
        return null;
    }

    boolean usernameAlreadyExists(String username) {
        return userRepository.users()
                .stream()
                .anyMatch(user -> user.getUsername().equals(username));
    }

    boolean emailAlreadyExists(String email) {
        return userRepository.users()
                .stream()
                .anyMatch(user -> user.getEmail().equals(email));
    }

}
