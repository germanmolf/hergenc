package com.example.heroes.versus.application.create;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.shared.domain.WordMother;
import com.example.heroes.versus.domain.VersusDefeatedMother;
import com.example.heroes.versus.domain.VersusIdMother;
import com.example.heroes.villains.domain.VillainIdMother;

public final class CreateVersusRequestMother {

    private static CreateVersusRequest create(String id, String heroId, String villainId, String defeated) {
        return new CreateVersusRequest(id, heroId, villainId, defeated);
    }

    public static CreateVersusRequest random() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.randomValue());
    }

    public static CreateVersusRequest withIdNull() {
        return create(null,
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.randomValue());
    }

    public static CreateVersusRequest withIdNotValid() {
        return create("qwe",
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.randomValue());
    }

    public static CreateVersusRequest withDefeatedNull() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                null);
    }

    public static CreateVersusRequest withDefeatedNotValid() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                WordMother.random());
    }

    public static CreateVersusRequest withHeroDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.heroDefeatedRandom().value());
    }

    public static CreateVersusRequest withVillainDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.villainDefeatedRandom().value());
    }
}
