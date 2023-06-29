package com.example.heroes.heroes.domain;

import com.example.heroes.shared.AggregateRoot;

public final class Hero extends AggregateRoot {

    private final HeroId id;
    private final HeroName name;
    private final HeroPower power;

    public Hero(String id, String name, String power) {
        this.id = new HeroId(id);
        this.name = new HeroName(name);
        this.power = new HeroPower(power);
        record(new HeroCreated(id, name, power));
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
}
