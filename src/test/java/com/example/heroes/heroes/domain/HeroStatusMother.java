package com.example.heroes.heroes.domain;

import java.util.Random;

public final class HeroStatusMother {

    private final static Random RANDOM = new Random();
    private final static HeroStatus[] statusValues = HeroStatus.values();

    public static HeroStatus random() {
        return statusValues[RANDOM.nextInt(statusValues.length)];
    }

    public static HeroStatus defeated() {
        return HeroStatus.DEFEATED;
    }

}
