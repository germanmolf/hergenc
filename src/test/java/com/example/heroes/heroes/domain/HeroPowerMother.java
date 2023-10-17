package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.WordMother;

public final class HeroPowerMother {

    public static HeroPower random() {
        return new HeroPower(WordMother.randomMinMax(1, 30));
    }
}
