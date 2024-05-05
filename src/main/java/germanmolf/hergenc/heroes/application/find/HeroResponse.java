package germanmolf.hergenc.heroes.application.find;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.villains.domain.VillainId;

import java.util.List;

public record HeroResponse(String id, String name, String power, List<String> villainsDefeated,
                           Integer villainsDefeatedTotal, String status, String villainDefeater) {

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
