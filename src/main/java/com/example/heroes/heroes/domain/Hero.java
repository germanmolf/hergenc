package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.AggregateRoot;
import com.example.heroes.villains.domain.VillainId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public final class Hero extends AggregateRoot {

    private final HeroId id;
    private final HeroName name;
    private final HeroPower power;
    private Integer villainsDefeatedTotal;
    private final List<VillainId> villainsDefeated;
    private String status;
    private Optional<VillainId> villainDefeater;

    public Hero(String id, String name, String power, Integer villainsDefeatedTotal, List<VillainId> villainsDefeated,
                String status, Optional<String> villainDefeater) {
        this.id = new HeroId(id);
        this.name = new HeroName(name);
        this.power = new HeroPower(power);
        this.villainsDefeatedTotal = villainsDefeatedTotal;
        this.villainsDefeated = villainsDefeated;
        this.status = status;
        this.villainDefeater = villainDefeater.isPresent() ? villainDefeater.map(VillainId::new) : Optional.empty();
    }

    public static Hero create(String id, String name, String power) {
        Hero hero = new Hero(id, name, power, 0, new ArrayList<>(), "active", Optional.empty());
        hero.record(new HeroCreatedEvent(id, name, power));
        return hero;
    }

    public boolean hasDefeated(VillainId villainId) {
        return villainsDefeated.contains(villainId);
    }

    public void villainDefeated(VillainId villainId) {
        villainsDefeatedTotal++;
        villainsDefeated.add(villainId);
    }

    public void defeatedBy(VillainId villainId) {
        status = "defeated";
        villainDefeater = Optional.of(villainId);
    }

    public HeroId id() {
        return id;
    }

    public HeroName name() {
        return name;
    }

    public HeroPower power() {
        return power;
    }

    public Integer villainsDefeatedTotal() {
        return villainsDefeatedTotal;
    }

    public List<VillainId> villainsDefeated() {
        return villainsDefeated;
    }

    public String status() {
        return status;
    }

    public Optional<VillainId> villainDefeater() {
        return villainDefeater;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(id, hero.id) && Objects.equals(name, hero.name) && Objects.equals(power, hero.power) && Objects.equals(villainsDefeatedTotal, hero.villainsDefeatedTotal) && Objects.equals(villainsDefeated, hero.villainsDefeated) && Objects.equals(status, hero.status) && Objects.equals(villainDefeater, hero.villainDefeater);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power, villainsDefeatedTotal, villainsDefeated, status, villainDefeater);
    }
}
