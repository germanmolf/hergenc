package com.example.heroes.heroes.domain;

public final class HeroCreatedMother {

    public static HeroCreated random() {
        return new HeroCreated(HeroIdMother.random(), HeroNameMother.random(), HeroPowerMother.random());
    }

    public static HeroCreated fromHero(Hero hero) {
        return new HeroCreated(hero.getId(), hero.getName(), hero.getPower());
    }
}
