package com.durys.jakub.accessmanagement.shared.mails.service;

import com.durys.jakub.accessmanagement.shared.mails.model.MailWithTemporaryPasswordDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
public class MailSenderService {

    private final WebClient mailClient;

    public void send(MailWithTemporaryPasswordDTO mail) {
       mailClient.post()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(mail)).exchange().block();
    }

}
