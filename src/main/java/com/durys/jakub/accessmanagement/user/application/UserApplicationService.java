package com.durys.jakub.accessmanagement.user.application;

import com.durys.jakub.accessmanagement.ddd.annotation.ApplicationService;
import com.durys.jakub.accessmanagement.role.domain.Role;
import com.durys.jakub.accessmanagement.shared.mails.model.MailWithTemporaryPasswordDTO;
import com.durys.jakub.accessmanagement.shared.mails.service.MailSenderService;
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

    public void disableUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        user.setEnabled(false);
        userRepository.save(user);
    }

    public void deleteUser(String id) {

        User user = userRepository.findById(id)
                .orElseThrow(RuntimeException::new);

        userRepository.delete(user);
        userRepository.save(user);
    }

    public void setRoles(String id, List<Role> roles) {
        userRepository.setRoles(id, roles);
    }

}
