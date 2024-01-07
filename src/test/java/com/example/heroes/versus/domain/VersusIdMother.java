package com.example.heroes.versus.domain;

import com.example.heroes.shared.domain.UuidMother;

public final class VersusIdMother {

    public static VersusId random() {
        return new VersusId(UuidMother.random());
    }

    public static String randomValue() {
        return random().value();
    }

    public static VersusId create(String id) {
        return new VersusId(id);
    }
}
