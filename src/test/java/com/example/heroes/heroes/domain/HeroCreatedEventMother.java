package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;

public final class HeroCreatedEventMother {

    private static HeroCreatedEvent create(String id, String name, String power) {
        return new HeroCreatedEvent(id, name, power);
    }

    public static HeroCreatedEvent random() {
        return create(HeroIdMother.random().value(), HeroNameMother.random().value(),
                HeroPowerMother.random().value());
    }

    public static HeroCreatedEvent fromAggregate(Hero hero) {
        return create(hero.getId().value(), hero.getName().value(), hero.getPower().value());
    }

    public static HeroCreatedEvent fromRequest(CreateHeroRequest request) {
        return create(request.getId(), request.getName(), request.getPower());
    }
}
