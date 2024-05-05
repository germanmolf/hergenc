package germanmolf.hergenc.versus.domain;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.villains.domain.VillainId;

public final class VersusCriteria {

    public static Criteria heroDefeated(HeroId id) {
        return new Criteria()
                .setFilter("defeated", "in", "hero,both")
                .setFilter("heroId", "=", id.value());
    }

    public static Criteria villainDefeated(VillainId id) {
        return new Criteria()
                .setFilter("defeated", "in", "villain,both")
                .setFilter("villainId", "=", id.value());
    }
}
