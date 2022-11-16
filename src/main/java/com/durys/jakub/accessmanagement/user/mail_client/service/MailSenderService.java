package com.durys.jakub.accessmanagement.user.mail_client.service;

import com.durys.jakub.accessmanagement.user.mail_client.model.MailWithTemporaryPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class MailSenderService {

    private final WebClient.Builder mailClient;

    public void send(MailWithTemporaryPasswordDTO mail) {
        mailClient
                .build()
                .post()
                .body(BodyInserters.fromValue(mail)).exchange();
    }

}
