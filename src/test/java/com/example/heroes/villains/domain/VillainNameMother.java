package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.WordMother;

public final class VillainNameMother {

    private static VillainName random() {
        return new VillainName(WordMother.randomMinMax(1, 20));
    }

    public static String randomValue() {
        return random().value();
    }
}
