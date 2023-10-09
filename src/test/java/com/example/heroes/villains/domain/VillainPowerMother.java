package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.WordMother;

public final class VillainPowerMother {

    public static String random() {
        return WordMother.randomMinMax(1, 30);
    }
}
