package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.IntValueObject;

public final class VillainHeroesDefeatedTotal extends IntValueObject {

    public VillainHeroesDefeatedTotal(Integer value) {
        super(value);
    }

    public static VillainHeroesDefeatedTotal initialize() {
        return new VillainHeroesDefeatedTotal(0);
    }

    public VillainHeroesDefeatedTotal increment() {
        return new VillainHeroesDefeatedTotal(value() + 1);
    }
}
