package com.durys.jakub.accessmanagement.user.mail_client.model;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MailWithTemporaryPasswordDTO {
    private String destination;
    private String title;
    private String content;
}
