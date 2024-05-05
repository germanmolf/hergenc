package germanmolf.hergenc.versus.domain;

import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.versus.application.create.CreateVersusRequest;
import germanmolf.hergenc.villains.domain.VillainIdMother;

public final class VersusMother {

    private static Versus create(String id, String heroId, String villainId, String defeated) {
        return new Versus(id, heroId, villainId, defeated);
    }

    public static Versus random() {
        return create(VersusIdMother.randomValue(),
                HeroIdMother.randomValue(),
                VillainIdMother.randomValue(),
                VersusDefeatedMother.randomValue());
    }

    public static Versus create(String heroId, String villainId, String defeated) {
        return create(VersusIdMother.randomValue(), heroId, villainId, defeated);
    }

    public static Versus fromRequest(CreateVersusRequest request) {
        return create(request.id(), request.heroId(), request.villainId(), request.defeated());
    }
}
