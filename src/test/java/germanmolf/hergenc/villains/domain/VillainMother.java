package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.shared.domain.ListMother;
import germanmolf.hergenc.villains.application.create.CreateVillainRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public final class VillainMother {

    private static Villain create(String id, String name, String power) {
        return new Villain(id, name, power);
    }

    public static Villain random() {
        List<String> heroesDefeated = ListMother.random(HeroIdMother::randomValue);
        VillainStatus status = VillainStatusMother.random();
        return new Villain(VillainIdMother.randomValue(),
                VillainNameMother.randomValue(),
                VillainPowerMother.randomValue(),
                heroesDefeated,
                heroesDefeated.size(),
                status.value(),
                status.isActive() ? Optional.empty() : Optional.of(HeroIdMother.randomValue()));
    }

    public static Villain fromRequest(CreateVillainRequest request) {
        return create(request.id(), request.name(), request.power());
    }

    public static Villain create(String name, String power) {
        return create(VillainIdMother.randomValue(), name, power);
    }

    public static Villain updatingHeroesDefeated(Villain villain, HeroId heroId) {
        List<HeroId> heroIds = new ArrayList<>(villain.heroesDefeated());
        heroIds.add(heroId);
        List<String> heroesDefeated = heroIds.stream().map(HeroId::value).toList();

        return new Villain(villain.id().value(),
                villain.name().value(),
                villain.power().value(),
                heroesDefeated,
                heroesDefeated.size(),
                villain.status().value(),
                villain.heroDefeater().map(HeroId::value));
    }

    public static Villain withStatusActive() {
        List<String> heroesDefeated = ListMother.random(HeroIdMother::randomValue);
        return new Villain(VillainIdMother.randomValue(),
                VillainNameMother.randomValue(),
                VillainPowerMother.randomValue(),
                heroesDefeated,
                heroesDefeated.size(),
                VillainStatusMother.active().value(),
                Optional.empty());
    }

    public static Villain updatingStatusToDefeated(Villain villain, String heroId) {
        return new Villain(villain.id().value(),
                villain.name().value(),
                villain.power().value(),
                villain.heroesDefeated().stream().map(HeroId::value).toList(),
                villain.heroesDefeatedTotal().value(),
                VillainStatusMother.defeated().value(),
                Optional.of(heroId));
    }
}
