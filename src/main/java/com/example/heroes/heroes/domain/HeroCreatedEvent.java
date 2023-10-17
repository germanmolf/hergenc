package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.Objects;

public final class HeroCreatedEvent extends DomainEvent {

    private final String name;
    private final String power;

    public HeroCreatedEvent(String aggregateId, String name, String power) {
        super(aggregateId);
        this.name = name;
        this.power = power;
    }

    @Override
    public String eventName() {
        return "hero.created";
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
