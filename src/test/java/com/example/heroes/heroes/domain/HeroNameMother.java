package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.WordMother;

public final class HeroNameMother {

    private static HeroName random() {
        return new HeroName(WordMother.randomMinMax(1, 20));
    }

    public static String randomValue() {
        return random().value();
    }
}
