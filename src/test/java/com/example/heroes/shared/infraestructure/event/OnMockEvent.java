package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.Map;

public final class OnMockEvent extends DomainEvent {

    @Override
    public String eventName() {
        return "mock";
    }

    @Override
    public Map<String, String> toPrimitives() {
        return Map.of();
    }

    @Override
    public OnMockEvent fromPrimitives(String aggregateId, String eventId, String occurredOn, Map<String, String> body) {
        return new OnMockEvent();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
