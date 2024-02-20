package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MySqlDomainEventsConsumer {

    private final DomainEventRepositoryMySql repository;
    private final DomainEventSubscribersInformation domainEventSubscribersInformation;
    private boolean on = true;

    public MySqlDomainEventsConsumer(DomainEventRepositoryMySql repository, DomainEventSubscribersInformation domainEventSubscribersInformation) {
        this.repository = repository;
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
    }

    public void consume() {
        while (on) {
            List<DomainEventJpa> domainEvents = repository.searchAll();
            for (var eventJpa : domainEvents) {
                Class<? extends DomainEvent> eventClass = domainEventSubscribersInformation.getEventClass(eventJpa.getName());
                DomainEvent event = null;
                try {
                    event = eventClass.getConstructor().newInstance().fromPrimitives(eventJpa.getAggregateId(),
                            eventJpa.getId(), eventJpa.getOccurredOn(), eventJpa.getBody());
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                    continue;
                }
                Set<String> subscribersFailed = new HashSet<>();
                for (var subscriberName : eventJpa.getSubscribers()) {
                    DomainEventSubscriber<DomainEvent> subscriber =
                            domainEventSubscribersInformation.getSubscriber(subscriberName);
                    try {
                        subscriber.on(event);
                    } catch (Exception e) {
                        e.printStackTrace();
                        subscribersFailed.add(subscriberName);
                    }
                }
                if (subscribersFailed.isEmpty()) {
                    repository.delete(eventJpa);
                } else {
                    eventJpa.setSubscribers(subscribersFailed);
                    repository.save(eventJpa);
                }
            }
        }
    }

    public void stop() {
        on = false;
    }
}
