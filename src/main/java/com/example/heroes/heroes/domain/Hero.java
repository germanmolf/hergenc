package com.example.heroes.heroes.domain;

public final class Hero {

    private HeroId id;
    private HeroName name;
    private HeroPower power;

    public Hero(String id, String name, String power) {
        this.id = new HeroId(id);
        this.name = new HeroName(name);
        this.power = new HeroPower(power);
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
