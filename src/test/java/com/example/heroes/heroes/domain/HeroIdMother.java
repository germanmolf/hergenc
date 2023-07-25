package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.UuidMother;

public final class HeroIdMother {

    public static String random() {
        return UuidMother.random();
    }
}
