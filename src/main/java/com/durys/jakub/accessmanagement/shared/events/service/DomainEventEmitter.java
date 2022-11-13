package com.durys.jakub.accessmanagement.shared.events.service;

import com.durys.jakub.accessmanagement.shared.events.model.DomainEvent;

public interface DomainEventEmitter {

     <T extends DomainEvent> void emit(T t);
}
