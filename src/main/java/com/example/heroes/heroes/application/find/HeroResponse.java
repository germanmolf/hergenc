package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.Hero;

public final class HeroResponse {

    private final String id;
    private final String name;
    private final String power;

    public HeroResponse(String id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public static HeroResponse fromAggregate(Hero hero) {
        return new HeroResponse(hero.getId(), hero.getName(), hero.getPower());
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPower() {
        return power;
    }
}
