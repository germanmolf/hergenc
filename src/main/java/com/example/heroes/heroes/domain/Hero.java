package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.AggregateRoot;
import com.example.heroes.villains.domain.VillainId;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public final class Hero extends AggregateRoot {

    private final HeroId id;
    private final HeroName name;
    private final HeroPower power;
    private HeroVillainsDefeatedTotal villainsDefeatedTotal;
    private final List<VillainId> villainsDefeated;
    private HeroStatus status;
    private Optional<VillainId> villainDefeater;

    public Hero(String id, String name, String power, Integer villainsDefeatedTotal, List<String> villainsDefeated,
                String status, Optional<String> villainDefeater) {
        this.id = new HeroId(id);
        this.name = new HeroName(name);
        this.power = new HeroPower(power);
        this.villainsDefeatedTotal = new HeroVillainsDefeatedTotal(villainsDefeatedTotal);
        this.villainsDefeated = villainsDefeated.stream().map(VillainId::new).collect(Collectors.toList());
        this.status = HeroStatus.fromValue(status);
        this.villainDefeater = villainDefeater.map(VillainId::new);
    }

    public Hero(String id, String name, String power) {
        this.id = new HeroId(id);
        this.name = new HeroName(name);
        this.power = new HeroPower(power);
        this.villainsDefeatedTotal = HeroVillainsDefeatedTotal.initialize();
        this.villainsDefeated = new ArrayList<>();
        this.status = HeroStatus.ACTIVE;
        this.villainDefeater = Optional.empty();
    }

    public static Hero create(String id, String name, String power) {
        Hero hero = new Hero(id, name, power);
        hero.record(new HeroCreatedEvent(id, name, power));
        return hero;
    }

    public boolean hasDefeated(VillainId villainId) {
        return villainsDefeated.contains(villainId);
    }

    public void addVillainDefeated(VillainId villainId) {
        villainsDefeatedTotal = villainsDefeatedTotal.increment();
        villainsDefeated.add(villainId);
    }

    public void defeatedBy(VillainId villainId) {
        status = HeroStatus.DEFEATED;
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

    public HeroVillainsDefeatedTotal villainsDefeatedTotal() {
        return villainsDefeatedTotal;
    }

    public List<VillainId> villainsDefeated() {
        return villainsDefeated;
    }

    public HeroStatus status() {
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
