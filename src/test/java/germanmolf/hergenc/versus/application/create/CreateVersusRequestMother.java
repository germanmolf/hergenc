package germanmolf.hergenc.versus.application.create;

import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.shared.domain.WordMother;
import germanmolf.hergenc.versus.domain.VersusDefeatedMother;
import germanmolf.hergenc.versus.domain.VersusIdMother;
import germanmolf.hergenc.villains.domain.VillainIdMother;

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
