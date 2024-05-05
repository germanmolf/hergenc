package germanmolf.hergenc.versus.domain;

import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.versus.application.create.CreateVersusRequest;
import germanmolf.hergenc.villains.domain.VillainIdMother;

public final class VersusCreatedEventMother {

    private static VersusCreatedEvent create(String versusId, String heroId, String villainId, String defeated) {
        return new VersusCreatedEvent(versusId, heroId, villainId, defeated);
    }

    public static VersusCreatedEvent fromRequest(CreateVersusRequest request) {
        return create(request.id(), request.heroId(), request.villainId(), request.defeated());
    }

    public static VersusCreatedEvent random() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.randomValue());
    }

    public static VersusCreatedEvent withHeroDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.heroDefeatedRandom().value());
    }

    public static VersusCreatedEvent withVillainDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.villainDefeatedRandom().value());
    }

    public static VersusCreatedEvent withOnlyHeroDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.onlyHeroDefeated().value());
    }

    public static VersusCreatedEvent withOnlyVillainDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.onlyVillainDefeated().value());
    }

    public static VersusCreatedEvent withNoneDefeated() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.noneDefeated().value());
    }
}
