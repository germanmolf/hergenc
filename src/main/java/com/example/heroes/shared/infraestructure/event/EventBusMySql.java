package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;
import com.example.heroes.shared.domain.event.EventBus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public final class EventBusMySql implements EventBus {

    private final DomainEventRepositoryMySql repository;
    private final DomainEventSubscribersInformation domainEventSubscribersInformation;

    public EventBusMySql(DomainEventRepositoryMySql repository, DomainEventSubscribersInformation domainEventSubscribersInformation) {
        this.repository = repository;
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent event) {
        Set<String> subscribersNames =
                domainEventSubscribersInformation.getDomainEventSubscribersNames(event.eventName());
        EventQueued eventQueued = EventQueued.fromDomainEvent(event, subscribersNames);
        repository.save(eventQueued);
    }

}
