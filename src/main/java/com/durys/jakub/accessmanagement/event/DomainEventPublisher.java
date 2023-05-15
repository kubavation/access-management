package com.durys.jakub.accessmanagement.event;

public interface DomainEventPublisher {
    void emit(DomainEvent event);
}
