package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;

public final class HeroMother {

    private static Hero create(String id, String name, String power) {
        return new Hero(id, name, power);
    }

    public static Hero create(String id) {
        return new Hero(id, HeroNameMother.random().value(),
                HeroPowerMother.random().value());
    }

    public static Hero create(String name, String power) {
        return create(HeroIdMother.random().value(), name, power);
    }

    public static Hero random() {
        return create(
                HeroIdMother.random().value(),
                HeroNameMother.random().value(),
                HeroPowerMother.random().value()
        );
    }

    public static Hero fromRequest(CreateHeroRequest request) {
        return create(request.id(), request.name(), request.power());
    }
}
