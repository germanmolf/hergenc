package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.io.Serializable;
import java.util.HashMap;

public final class HeroCreated extends DomainEvent {

    private final String name;
    private final String power;

    public HeroCreated(String aggregateId, String name, String power) {
        super(aggregateId);
        this.name = name;
        this.power = power;
    }

    public HeroCreated(String aggregateId, String eventId, String occurredOn, String name, String power) {
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
        return new HeroCreated(aggregateId, eventId, occurredOn, (String) body.get("name"), (String) body.get("power"));
    }

}
