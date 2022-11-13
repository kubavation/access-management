package com.durys.jakub.accessmanagement.shared.events.model;

public interface DomainEvent {

    <T> T getData();
}
