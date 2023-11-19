package com.example.heroes.versus.application.create;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.shared.domain.WordMother;
import com.example.heroes.versus.domain.VersusDefeatedMother;
import com.example.heroes.versus.domain.VersusIdMother;
import com.example.heroes.villains.domain.VillainIdMother;

public final class CreateVersusRequestMother {

    private CreateVersusRequestMother() {
    }

    private static CreateVersusRequest create(String id, String heroId, String villainId, String defeated) {
        return new CreateVersusRequest(id, heroId, villainId, defeated);
    }

    public static CreateVersusRequest random() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.random().value());
    }

    public static CreateVersusRequest withDefeatedNull() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                null);
    }

    public static CreateVersusRequest withDefeatedNotValid() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                WordMother.random());
    }

    public static CreateVersusRequest withHeroDefeated() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.heroDefeatedRandom().value());
    }

    public static CreateVersusRequest withVillainDefeated() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VillainIdMother.random().value(),
                VersusDefeatedMother.villainDefeatedRandom().value());
    }
}
