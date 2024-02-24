package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.Map;
import java.util.Set;

public final class EventQueuedMother {

    private static final Map<String, Set<String>> SUBSCRIBERS_OF_EVENTS = Map.of(
            "hero.created", Set.of("hero-subscriber.mock"),
            "villain.created", Set.of("villain-subscriber.mock", "villain-subscriber.mock-fails")
    );

    public static EventQueued fromDomainEvent(DomainEvent event) {
        return new EventQueued(event.eventId(),
                event.aggregateId(),
                event.occurredOn(),
                event.eventName(),
                SUBSCRIBERS_OF_EVENTS.get(event.eventName()),
                event.toPrimitives());
    }

    public static EventQueued withSubscribers(DomainEvent event, String... subscribers) {
        return new EventQueued(event.eventId(),
                event.aggregateId(),
                event.occurredOn(),
                event.eventName(),
                Set.of(subscribers),
                event.toPrimitives());
    }
}
