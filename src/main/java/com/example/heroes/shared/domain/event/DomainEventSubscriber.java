package com.example.heroes.shared.domain.event;

public interface DomainEventSubscriber<T extends DomainEvent> {

    void on(T event);

    String subscriberName();

}
