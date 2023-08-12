package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.WordMother;

public final class HeroPowerMother {

    public static String randomValue() {
        return WordMother.randomMinMax(1, 30);
    }
}
