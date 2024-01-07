package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.WordMother;

public final class VillainPowerMother {

    private static VillainPower random() {
        return new VillainPower(WordMother.randomMinMax(1, 30));
    }

    public static String randomValue() {
        return random().value();
    }
}
