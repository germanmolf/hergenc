package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.Hero;

import java.util.Objects;

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
        return new HeroResponse(hero.getId().value(), hero.getName().value(), hero.getPower().value());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HeroResponse that = (HeroResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power);
    }
}
