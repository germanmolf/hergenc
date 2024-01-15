package com.example.heroes.shared.domain.event;

import com.example.heroes.shared.domain.Utils;

import java.time.LocalDateTime;
import java.util.HashMap;
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

    protected DomainEvent(String aggregateId, String eventId, String occurredOn) {
        this.aggregateId = aggregateId;
        this.eventId = eventId;
        this.occurredOn = occurredOn;
    }

    public abstract String eventName();

    public abstract HashMap<String, String> toPrimitives();

    public abstract DomainEvent fromPrimitives(
            String aggregateId,
            String eventId,
            String occurredOn,
            HashMap<String, String> body
    );

    public String aggregateId() {
        return aggregateId;
    }

    public String eventId() {
        return eventId;
    }

    public String occurredOn() {
        return occurredOn;
    }
}
