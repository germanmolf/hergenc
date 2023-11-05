package com.example.heroes.versus.domain;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.villains.domain.VillainId;

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
