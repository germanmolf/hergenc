package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.Hero;

public final class HeroResponseMother {

    public static HeroResponse fromAggregate(Hero hero) {
        return new HeroResponse(hero.id().value(), hero.name().value(), hero.power().value());
    }
}
