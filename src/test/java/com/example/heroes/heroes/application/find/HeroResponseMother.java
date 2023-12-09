package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.villains.domain.VillainId;

public final class HeroResponseMother {

    public static HeroResponse fromAggregate(Hero hero) {
        return new HeroResponse(hero.id().value(),
                hero.name().value(),
                hero.power().value(),
                hero.villainsDefeated().stream().map(VillainId::value).toList(),
                hero.villainsDefeatedTotal().value(),
                hero.status().value(),
                hero.villainDefeater().map(VillainId::value).orElse(null));
    }
}
