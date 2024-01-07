package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.application.create.CreateHeroRequest;
import com.example.heroes.shared.domain.ListMother;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainIdMother;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public final class HeroMother {

    private static Hero create(String id, String name, String power) {
        return new Hero(id, name, power);
    }

    public static Hero create(String name, String power) {
        return create(HeroIdMother.randomValue(), name, power);
    }

    public static Hero random() {
        List<String> villainsDefeated = ListMother.random(VillainIdMother::randomValue);
        HeroStatus status = HeroStatusMother.random();
        return new Hero(HeroIdMother.randomValue(),
                HeroNameMother.randomValue(),
                HeroPowerMother.randomValue(),
                villainsDefeated.size(),
                villainsDefeated,
                status.value(),
                status.isActive() ? Optional.empty() : Optional.of(VillainIdMother.randomValue()));
    }

    public static Hero fromRequest(CreateHeroRequest request) {
        return create(request.id(), request.name(), request.power());
    }

    public static Hero createWithId(String heroId) {
        return create(heroId, HeroNameMother.randomValue(), HeroPowerMother.randomValue());
    }

    public static Hero updatingVillainsDefeated(Hero hero, String villainId) {
        List<String> villainsDefeated = hero.villainsDefeated().stream().map(VillainId::value).collect(Collectors.toList());
        villainsDefeated.add(villainId);

        return new Hero(hero.id().value(), hero.name().value(), hero.power().value(),
                villainsDefeated.size(), villainsDefeated, hero.status().value(),
                hero.villainDefeater().map(VillainId::value));
    }

    public static Hero updatingHeroDefeated(Hero hero, String villainId) {
        return new Hero(hero.id().value(),
                hero.name().value(),
                hero.power().value(),
                hero.villainsDefeatedTotal().value(),
                hero.villainsDefeated().stream().map(VillainId::value).toList(),
                HeroStatusMother.defeated().value(),
                Optional.of(villainId));
    }

    public static Hero copy(Hero hero) {
        return new Hero(hero.id().value(),
                hero.name().value(),
                hero.power().value(),
                hero.villainsDefeatedTotal().value(),
                hero.villainsDefeated().stream().map(VillainId::value).toList(),
                hero.status().value(),
                hero.villainDefeater().map(VillainId::value));
    }
}
