package com.durys.jakub.accessmanagement.user.mail_client.configuration;

import com.durys.jakub.accessmanagement.user.mail_client.service.MailSenderService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
    @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

    @Bean
    WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(mailService).build();
    }

    @Bean
    MailSenderService mailSenderService(WebClient mailClient) {
        return new MailSenderService(mailClient);
    }

}
