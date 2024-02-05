package com.example.heroes.versus.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.HashMap;
import java.util.Objects;

public final class VersusCreatedEvent extends DomainEvent {

    private final String heroId;
    private final String villainId;
    private final String defeated;

    public VersusCreatedEvent() {
        super();
        heroId = null;
        villainId = null;
        defeated = null;
    }

    public VersusCreatedEvent(String aggregateId, String heroId, String villainId, String defeated) {
        super(aggregateId);
        this.heroId = heroId;
        this.villainId = villainId;
        this.defeated = defeated;
    }

    public VersusCreatedEvent(String aggregateId, String eventId, String occurredOn, String heroId, String villainId,
                              String defeated) {
        super(aggregateId, eventId, occurredOn);
        this.heroId = heroId;
        this.villainId = villainId;
        this.defeated = defeated;
    }

    @Override
    public String eventName() {
        return "versus.created";
    }

    @Override
    public HashMap<String, String> toPrimitives() {
        return new HashMap<>() {{
            put("heroId", heroId);
            put("villainId", villainId);
            put("defeated", defeated);
        }};
    }

    @Override
    public VersusCreatedEvent fromPrimitives(String aggregateId, String eventId, String occurredOn,
                                             HashMap<String, String> body) {
        return new VersusCreatedEvent(aggregateId, eventId, occurredOn, body.get("heroId"), body.get("villainId"),
                body.get("defeated"));
    }

    public String heroId() {
        return heroId;
    }

    public String villainId() {
        return villainId;
    }

    public String defeated() {
        return defeated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VersusCreatedEvent that = (VersusCreatedEvent) o;
        return Objects.equals(heroId, that.heroId) && Objects.equals(villainId, that.villainId) && Objects.equals(defeated, that.defeated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(heroId, villainId, defeated);
    }
}
