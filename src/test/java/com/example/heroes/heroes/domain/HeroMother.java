package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;

public final class HeroMother {

    public static Hero create(String id, String name, String power) {
        return new Hero(id, name, power);
    }

    public static Hero random() {
        return new Hero(
                HeroIdMother.random(),
                HeroNameMother.random(),
                HeroPowerMother.random()
        );
    }

    public static Hero fromRequest(CreateHeroRequest request) {
        return create(request.getId(), request.getName(), request.getPower());
    }
}
