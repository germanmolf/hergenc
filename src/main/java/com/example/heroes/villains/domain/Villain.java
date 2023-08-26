package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.AggregateRoot;

import java.util.Objects;

public final class Villain extends AggregateRoot {

    private final VillainId id;
    private final VillainName name;

    public Villain(String id, String name) {
        this.id = new VillainId(id);
        this.name = new VillainName(name);
    }

    public static Villain create(String id, String name) {
        Villain villain = new Villain(id, name);
        villain.record(new VillainCreatedEvent(id, name));
        return villain;
    }

    public VillainId getId() {
        return id;
    }

    public VillainName getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villain villain = (Villain) o;
        return Objects.equals(id, villain.id) && Objects.equals(name, villain.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
