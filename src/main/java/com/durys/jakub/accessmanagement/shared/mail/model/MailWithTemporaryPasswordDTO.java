package com.durys.jakub.accessmanagement.shared.mail.model;

import lombok.*;

@AllArgsConstructor
@Getter
public class MailWithTemporaryPasswordDTO {
    private String destination;
    private String title;
    private String content;

    public static MailWithTemporaryPasswordDTO from(String destination, String password) {
        return new MailWithTemporaryPasswordDTO(destination, "Account successfully created", String.format("Your temporary password: %s", password));
    }
}
