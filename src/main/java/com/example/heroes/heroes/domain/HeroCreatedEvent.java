package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Objects;

public final class HeroCreatedEvent extends DomainEvent {

    private final String name;
    private final String power;

    public HeroCreatedEvent(String aggregateId, String name, String power) {
        super(aggregateId);
        this.name = name;
        this.power = power;
    }

    public HeroCreatedEvent(String aggregateId, String eventId, String occurredOn, String name, String power) {
        super(aggregateId, eventId, occurredOn);
        this.name = name;
        this.power = power;
    }

    @Override
    public String eventName() {
        return "hero.created";
    }

    @Override
    public HashMap<String, Serializable> toPrimitives() {
        return new HashMap<>() {{
            put("name", name);
            put("power", power);
        }};
    }

    @Override
    public DomainEvent fromPrimitives(String aggregateId, HashMap<String, Serializable> body, String eventId, String occurredOn) {
        return new HeroCreatedEvent(aggregateId, eventId, occurredOn, (String) body.get("name"), (String) body.get("power"));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroCreatedEvent that = (HeroCreatedEvent) o;
        return Objects.equals(name, that.name) && Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, power);
    }
}
