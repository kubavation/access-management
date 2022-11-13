package com.durys.jakub.accessmanagement.shared.events.service;

import com.durys.jakub.accessmanagement.shared.events.model.DomainEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class SpringDomainEventEmitter implements DomainEventEmitter {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public <T extends DomainEvent> void emit(T event) {
        applicationEventPublisher.publishEvent(event);
    }
}
