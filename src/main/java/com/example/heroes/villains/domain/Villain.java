package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Villain extends AggregateRoot {

    private final VillainId id;
    private final VillainName name;
    private final VillainPower power;

    public Villain(String id, String name, String power) {
        this.id = new VillainId(id);
        this.name = new VillainName(name);
        this.power = new VillainPower(power);
    }

    public static Villain create(String id, String name, String power) {
        Villain villain = new Villain(id, name, power);
        villain.record(new VillainCreatedEvent(id, name, power));
        return villain;
    }

    public VillainId getId() {
        return id;
    }

    public VillainName getName() {
        return name;
    }

    public VillainPower getPower() {
        return power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villain villain = (Villain) o;
        return Objects.equals(id, villain.id) && Objects.equals(name, villain.name) && Objects.equals(power, villain.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power);
    }
}
