package com.durys.jakub.accessmanagement.user.mail_client.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MailWithTemporaryPasswordDTO {
    private String destination;
    private String title;
    private String content;

    public static MailWithTemporaryPasswordDTO from(String destination, String password) {
        return new MailWithTemporaryPasswordDTO(destination, "Account successfully created", String.format("Your temporary password: %s", password));
    }
}
