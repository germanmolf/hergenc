package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Hero extends AggregateRoot {

    private final HeroId id;
    private final HeroName name;
    private final HeroPower power;

    public Hero(String id, String name, String power) {
        this.id = new HeroId(id);
        this.name = new HeroName(name);
        this.power = new HeroPower(power);
    }

    public static Hero create(String id, String name, String power) {
        Hero hero = new Hero(id, name, power);
        hero.record(new HeroCreated(id, name, power));
        return hero;
    }

    public String getId() {
        return id.value();
    }

    public String getName() {
        return name.value();
    }

    public String getPower() {
        return power.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return Objects.equals(id, hero.id) && Objects.equals(name, hero.name) && Objects.equals(power, hero.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power);
    }
}
