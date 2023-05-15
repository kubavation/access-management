package com.durys.jakub.accessmanagement.role.domain.event;

import com.durys.jakub.accessmanagement.event.DomainEvent;

public record RoleDeletedEvent(String name) implements DomainEvent {}
