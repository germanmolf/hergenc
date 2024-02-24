package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.event.DomainEvent;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Component
public class MySqlDomainEventsConsumer {

    private static final Criteria firstTheOldestOnesCriteria = EventQueuedCriteria.firstTheOldestOnes();
    private final DomainEventRepositoryMySql repository;
    private final DomainEventSubscribersInformation subscribersInformation;
    private boolean on = true;

    public MySqlDomainEventsConsumer(DomainEventRepositoryMySql repository, DomainEventSubscribersInformation subscribersInformation) {
        this.repository = repository;
        this.subscribersInformation = subscribersInformation;
    }

    public void stop() {
        on = false;
    }

    public void consume() {
        while (on) {
            List<EventQueued> eventsQueued = repository.search(firstTheOldestOnesCriteria);
            for (var eventQueued : eventsQueued) {
                Class<? extends DomainEvent> eventClass = subscribersInformation.getEventClass(eventQueued.getName());
                DomainEvent event;
                try {
                    event = createInstance(eventClass, eventQueued);
                } catch (ReflectiveOperationException e) {
                    e.printStackTrace();
                    continue;
                }
                callSubscribers(eventQueued, event);
                if (eventQueued.hasSubscribers()) {
                    repository.save(eventQueued);
                } else {
                    repository.delete(eventQueued);
                }
            }
        }
    }

    private DomainEvent createInstance(Class<? extends DomainEvent> eventClass, EventQueued eventQueued) throws ReflectiveOperationException {
        DomainEvent instance = eventClass.getConstructor().newInstance();
        return instance.fromPrimitives(eventQueued.getAggregateId(), eventQueued.getId(), eventQueued.getOccurredOn(), eventQueued.getBody());
    }

    private void callSubscribers(EventQueued eventQueued, DomainEvent event) {
        Set<String> subscribersFailed = new HashSet<>();
        for (var subscriberName : eventQueued.getSubscribers()) {
            DomainEventSubscriber<DomainEvent> subscriber =
                    subscribersInformation.getSubscriber(subscriberName);
            try {
                subscriber.on(event);
            } catch (Exception e) {
                e.printStackTrace();
                subscribersFailed.add(subscriberName);
            }
        }
        eventQueued.setSubscribers(subscribersFailed);
    }

    public void withSubscribersInformation(Map<String, Set<String>> subscribersOfEvents,
                                           Map<String, Class<? extends DomainEvent>> eventsClasses,
                                           Map<String, DomainEventSubscriber<DomainEvent>> subscribers) {
        subscribersInformation.withInformation(subscribersOfEvents, eventsClasses, subscribers);
    }
}
