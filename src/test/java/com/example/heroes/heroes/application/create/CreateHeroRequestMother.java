package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.heroes.domain.HeroNameMother;
import com.example.heroes.heroes.domain.HeroPowerMother;

public final class CreateHeroRequestMother {

    public static CreateHeroRequest create(String id, String name, String power) {
        return new CreateHeroRequest(id, name, power);
    }

    public static CreateHeroRequest random() {
        return new CreateHeroRequest(HeroIdMother.randomValue(), HeroNameMother.randomValue(), HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest fromAggregate(Hero hero) {
        return new CreateHeroRequest(hero.getId().value(), hero.getName().value(), hero.getPower().value());
    }
}
