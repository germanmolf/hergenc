package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.Objects;

public final class VillainCreatedEvent extends DomainEvent {

    private final String name;
    private final String power;

    public VillainCreatedEvent(String aggregateId, String name, String power) {
        super(aggregateId);
        this.name = name;
        this.power = power;
    }


    @Override
    public String eventName() {
        return "villain.created";
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
