package com.durys.jakub.accessmanagement.user.mail_client.configuration;

import com.durys.jakub.accessmanagement.user.mail_client.service.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
class MailClientConfiguration {

    private final String mailService;

    public MailClientConfiguration(@Value("${mail-service-url}") String mailService) {
        this.mailService = mailService;
    }

    @Bean
    WebClient.Builder mailClientBuilder() {
        return WebClient.builder()
                .baseUrl(mailService);
    }

    @Bean
    MailSenderService mailSenderService(WebClient.Builder mailClientBuilder) {
        return new MailSenderService(mailClientBuilder);
    }

}
