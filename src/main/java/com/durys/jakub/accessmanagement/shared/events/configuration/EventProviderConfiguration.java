package com.durys.jakub.accessmanagement.shared.events.configuration;

import com.durys.jakub.accessmanagement.shared.events.service.DomainEventEmitter;
import com.durys.jakub.accessmanagement.shared.events.service.SpringDomainEventEmitter;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class EventProviderConfiguration {

    @Bean
    DomainEventEmitter eventEmitter(ApplicationEventPublisher eventPublisher) {
        return new SpringDomainEventEmitter(eventPublisher);
    }
}
