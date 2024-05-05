package germanmolf.hergenc.villains.application.find;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.villains.application.find.VillainResponse;
import germanmolf.hergenc.villains.domain.Villain;

public final class VillainResponseMother {

    public static VillainResponse fromAggregate(Villain villain) {
        return new VillainResponse(villain.id().value(),
                villain.name().value(),
                villain.power().value(),
                villain.heroesDefeated().stream().map(HeroId::value).toList(),
                villain.heroesDefeatedTotal().value(),
                villain.status().value(),
                villain.heroDefeater().map(HeroId::value).orElse(null));
    }
}
