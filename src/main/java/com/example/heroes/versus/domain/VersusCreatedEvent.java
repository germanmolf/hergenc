package com.example.heroes.versus.domain;

import com.example.heroes.shared.domain.event.DomainEvent;

import java.util.Objects;

public final class VersusCreatedEvent extends DomainEvent {

    private final String heroId;
    private final String villainId;
    private final String defeated;

    public VersusCreatedEvent(String aggregateId, String heroId, String villainId, String defeated) {
        super(aggregateId);
        this.heroId = heroId;
        this.villainId = villainId;
        this.defeated = defeated;
    }

    @Override
    public String eventName() {
        return "versus.created";
    }

    public String getHeroId() {
        return heroId;
    }

    public String getVillainId() {
        return villainId;
    }

    public String getDefeated() {
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
