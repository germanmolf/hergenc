package com.example.heroes.versus.domain;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.AggregateRoot;
import com.example.heroes.versus.domain.exceptions.VersusDefeatedInvalidValueException;
import com.example.heroes.versus.domain.exceptions.VersusDefeatedNullException;
import com.example.heroes.villains.domain.VillainId;

import java.util.Objects;

public final class Versus extends AggregateRoot {

    private final VersusId id;
    private final HeroId heroId;
    private final VillainId villainId;
    private final String defeated;

    public Versus(String id, String heroId, String villainId, String defeated) {
        this.id = new VersusId(id);
        this.heroId = new HeroId(heroId);
        this.villainId = new VillainId(villainId);
        if (defeated == null) {
            throw new VersusDefeatedNullException();
        }
        if (!(defeated.equals("hero") || defeated.equals("villain") || defeated.equals("both") || defeated.equals(
                "none"))) {
            throw new VersusDefeatedInvalidValueException(defeated);
        }
        this.defeated = defeated;
    }

    public static Versus create(String id, String heroId, String villainId, String defeated) {
        Versus versus = new Versus(id, heroId, villainId, defeated);
        versus.record(new VersusCreatedEvent(id, heroId, villainId, defeated));
        return versus;
    }

    public VersusId getId() {
        return id;
    }

    public HeroId getHeroId() {
        return heroId;
    }

    public VillainId getVillainId() {
        return villainId;
    }

    public String getDefeated() {
        return defeated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Versus versus = (Versus) o;
        return Objects.equals(id, versus.id) && Objects.equals(heroId, versus.heroId) && Objects.equals(villainId, versus.villainId) && Objects.equals(defeated, versus.defeated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, heroId, villainId, defeated);
    }
}
