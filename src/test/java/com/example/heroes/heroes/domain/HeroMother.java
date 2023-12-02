package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;
import com.example.heroes.villains.domain.VillainId;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class HeroMother {

    private static Hero create(String id, String name, String power) {
        return new Hero(id, name, power, 0, new ArrayList<>(), "active", Optional.empty());
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

    public static Hero createWithId(String heroId) {
        return create(heroId, HeroNameMother.random().value(), HeroPowerMother.random().value());
    }

    public static Hero updatingVillainsDefeated(Hero hero, String villainId) {
        List<VillainId> villainsDefeated = new ArrayList<>(hero.villainsDefeated());
        villainsDefeated.add(new VillainId(villainId));

        return new Hero(hero.id().value(), hero.name().value(), hero.power().value(),
                villainsDefeated.size(), villainsDefeated, hero.status(), hero.villainDefeater().map(VillainId::value));
    }

    public static Hero updatingHeroDefeated(Hero hero, String villainId) {
        return new Hero(hero.id().value(), hero.name().value(), hero.power().value(),
                0, new ArrayList<>(), "defeated", Optional.of(villainId));
    }

    public static Hero copy(Hero hero) {
        return new Hero(hero.id().value(), hero.name().value(), hero.power().value(),
                hero.villainsDefeatedTotal(), hero.villainsDefeated(), hero.status(),
                hero.villainDefeater().map(VillainId::value));
    }
}
