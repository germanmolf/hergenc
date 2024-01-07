package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.WordMother;

public final class HeroPowerMother {

    private static HeroPower random() {
        return new HeroPower(WordMother.randomMinMax(1, 30));
    }

    public static String randomValue() {
        return random().value();
    }
}
