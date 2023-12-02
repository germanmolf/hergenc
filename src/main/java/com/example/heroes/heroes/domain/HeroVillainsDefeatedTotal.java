package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.IntValueObject;

public final class HeroVillainsDefeatedTotal extends IntValueObject {

    public HeroVillainsDefeatedTotal(Integer value) {
        super(value);
    }

    public static HeroVillainsDefeatedTotal initialize() {
        return new HeroVillainsDefeatedTotal(0);
    }

    public HeroVillainsDefeatedTotal increment() {
        return new HeroVillainsDefeatedTotal(value() + 1);
    }
}
