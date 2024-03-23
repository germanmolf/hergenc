package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public final class VillainCreatedEvent extends DomainEvent {

    private final String name;
    private final String power;

    public VillainCreatedEvent() {
        super();
        name = null;
        power = null;
    }

    public VillainCreatedEvent(String aggregateId, String name, String power) {
        super(aggregateId);
        this.name = name;
        this.power = power;
    }

    public VillainCreatedEvent(String aggregateId, String eventId, String occurredOn, String name, String power) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.power = power;
    }

    @Override
    public String eventName() {
        return "villain.created";
    }

    @Override
    public Map<String, String> toPrimitives() {
        return new HashMap<>() {{
            put("name", name);
            put("power", power);
        }};
    }

    @Override
    public VillainCreatedEvent fromPrimitives(String aggregateId, String eventId, String occurredOn, Map<String, String> body) {
        return new VillainCreatedEvent(aggregateId, eventId, occurredOn, body.get("name"), body.get("power"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VillainCreatedEvent that = (VillainCreatedEvent) o;
        return Objects.equals(name, that.name) && Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power);
    }
}
