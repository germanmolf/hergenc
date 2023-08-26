package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.UuidMother;

public final class VillainIdMother {

    public static String randomValue() {
        return UuidMother.random();
    }
}
