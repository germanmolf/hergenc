package germanmolf.hergenc.villains.application.find;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.villains.domain.Villain;

import java.util.List;

public record VillainResponse(String id, String name, String power, List<String> heroesDefeated,
                              Integer heroesDefeatedTotal, String status, String heroDefeater) {

    public static VillainResponse fromAggregate(Villain villain) {
        return new VillainResponse(villain.id().value(),
                villain.name().value(),
                villain.power().value(),
                villain.heroesDefeated().stream().map(HeroId::value).toList(),
                villain.heroesDefeatedTotal().value(),
                villain.status().value(),
                villain.heroDefeater().map(HeroId::value).orElse(null)
        );
    }

}
