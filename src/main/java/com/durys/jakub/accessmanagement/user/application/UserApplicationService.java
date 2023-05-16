package com.durys.jakub.accessmanagement.user.application;

import com.durys.jakub.accessmanagement.ddd.annotation.ApplicationService;
import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.shared.exception.EntityNotFoundException;
import com.durys.jakub.accessmanagement.shared.mail.model.MailWithTemporaryPasswordDTO;
import com.durys.jakub.accessmanagement.shared.mail.service.MailSenderService;
import com.durys.jakub.accessmanagement.user.domain.User;
import com.durys.jakub.accessmanagement.user.domain.UserRepository;
import com.durys.jakub.accessmanagement.user.domain.UserValidator;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@ApplicationService
@RequiredArgsConstructor
public class UserApplicationService {

    private final UserRepository userRepository;
    private final MailSenderService mailSenderService;

    public void create(String username, String email) {

        User user = new User(UUID.randomUUID().toString(), username, email);

        Either<UserValidator.ValidationError, Void> validationResult = UserValidator
                .instance(userRepository)
                .validateUser(user);

        if (validationResult.isLeft()) {
            //todo handle validationResult.getLeft()
        }

        userRepository.save(user);
        mailSenderService.send(MailWithTemporaryPasswordDTO.from(null, null));
    }

    public void disable(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        user.setEnabled(false);
        userRepository.save(user);
    }

    public void enable(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        user.setEnabled(true);
        userRepository.save(user);
    }

    public void delete(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(User.class, id));

        userRepository.delete(user);
    }

    public void setRoles(String id, List<Role> roles) {
        userRepository.setRoles(id, roles);
    }

}
