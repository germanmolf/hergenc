package com.example.heroes.versus.domain;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.versus.application.create.CreateVersusRequest;
import com.example.heroes.villains.domain.VillainIdMother;

public final class VersusCreatedEventMother {

    private static VersusCreatedEvent create(String versusId, String heroId, String villainId, String defeated) {
        return new VersusCreatedEvent(versusId, heroId, villainId, defeated);
    }

    public static VersusCreatedEvent fromRequest(CreateVersusRequest request) {
        return create(request.id(), request.heroId(), request.villainId(), request.defeated());
    }

    public static VersusCreatedEvent random() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.random().value());
    }

    public static VersusCreatedEvent withOnlyHeroDefeated() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.onlyHeroDefeatedRandom().value());
    }

    public static VersusCreatedEvent withOnlyVillainDefeated() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.onlyVillainDefeatedRandom().value());
    }

    public static VersusCreatedEvent withNoneDefeated() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.noneDefeatedRandom().value());
    }
}
