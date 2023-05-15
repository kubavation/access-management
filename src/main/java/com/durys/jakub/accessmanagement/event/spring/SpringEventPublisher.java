package com.durys.jakub.accessmanagement.event.spring;


import com.durys.jakub.accessmanagement.event.DomainEvent;
import com.durys.jakub.accessmanagement.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;

@RequiredArgsConstructor
public class SpringEventPublisher implements DomainEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public void emit(DomainEvent event) {
        eventPublisher.publishEvent(event);
    }
}
