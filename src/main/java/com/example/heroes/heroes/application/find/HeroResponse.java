package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.Hero;

public record HeroResponse(String id, String name, String power) {

    public static HeroResponse fromAggregate(Hero hero) {
        return new HeroResponse(hero.id().value(),
                hero.name().value(),
                hero.power().value());
    }
}
