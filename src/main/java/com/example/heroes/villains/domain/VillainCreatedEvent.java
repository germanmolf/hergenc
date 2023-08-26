package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class VillainCreatedEvent extends DomainEvent {

    private final String name;

    public VillainCreatedEvent(String aggregateId, String name) {
        super(aggregateId);
        this.name = name;
    }

    public VillainCreatedEvent(String aggregateId, String eventId, String occurredOn, String name, String power) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
    }

    @Override
    public String eventName() {
        return "villain.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("name", name);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new VillainCreatedEvent(aggregateId, eventId, occurredOn, (String) body.get("name"), (String) body.get("power"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VillainCreatedEvent that = (VillainCreatedEvent) o;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
