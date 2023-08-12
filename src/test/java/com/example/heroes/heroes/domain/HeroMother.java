package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;

public final class HeroMother {

    public static Hero create(String id, String name, String power) {
        return new Hero(id, name, power);
    }

    public static Hero create(String name, String power) {
        return new Hero(HeroIdMother.randomValue(), name, power);
    }

    public static Hero random() {
        return new Hero(
                HeroIdMother.randomValue(),
                HeroNameMother.randomValue(),
                HeroPowerMother.randomValue()
        );
    }

    public static Hero fromRequest(CreateHeroRequest request) {
        return create(request.getId(), request.getName(), request.getPower());
    }
}
