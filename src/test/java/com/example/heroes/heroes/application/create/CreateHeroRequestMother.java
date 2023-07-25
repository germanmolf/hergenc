package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.heroes.domain.HeroNameMother;
import com.example.heroes.heroes.domain.HeroPowerMother;

public final class CreateHeroRequestMother {

    public static CreateHeroRequest create(String id, String name, String power) {
        return new CreateHeroRequest(id, name, power);
    }

    public static CreateHeroRequest random() {
        return new CreateHeroRequest(HeroIdMother.random(), HeroNameMother.random(), HeroPowerMother.random());
    }
}
