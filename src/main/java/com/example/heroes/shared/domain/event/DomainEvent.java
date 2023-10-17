package com.example.heroes.shared.domain.event;

import com.example.heroes.shared.domain.Utils;

import java.time.LocalDateTime;
import java.util.UUID;

public abstract class DomainEvent {

    private final String aggregateId;
    private final String eventId;
    private final String occurredOn;

    public DomainEvent(String aggregateId) {
        this.aggregateId = aggregateId;
        this.eventId = UUID.randomUUID().toString();
        this.occurredOn = Utils.dateToString(LocalDateTime.now());
    }

    public abstract String eventName();

    public String getAggregateId() {
        return aggregateId;
    }

    public String getEventId() {
        return eventId;
    }

    public String getOccurredOn() {
        return occurredOn;
    }
}
