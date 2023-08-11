package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;

public final class HeroCreatedEventMother {

    public static HeroCreatedEvent random() {
        return new HeroCreatedEvent(HeroIdMother.random(), HeroNameMother.random(), HeroPowerMother.random());
    }

    public static HeroCreatedEvent fromAggregate(Hero hero) {
        return new HeroCreatedEvent(hero.getId().value(), hero.getName().value(), hero.getPower().value());
    }

    public static HeroCreatedEvent fromRequest(CreateHeroRequest request) {
        return new HeroCreatedEvent(request.getId(), request.getName(), request.getPower());
    }
}
