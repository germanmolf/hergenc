package com.example.heroes.versus.domain;

import java.util.Random;

public final class VersusDefeatedMother {

    private final static Random RANDOM = new Random();
    private final static VersusDefeated[] defeatedValues = VersusDefeated.values();
    private final static VersusDefeated[] heroDefeatedValues = {VersusDefeated.HERO, VersusDefeated.BOTH};
    private final static VersusDefeated[] villainDefeatedValues = {VersusDefeated.VILLAIN, VersusDefeated.BOTH};

    public static VersusDefeated random() {
        return defeatedValues[RANDOM.nextInt(defeatedValues.length)];
    }

    public static VersusDefeated heroDefeatedRandom() {
        return heroDefeatedValues[RANDOM.nextInt(heroDefeatedValues.length)];
    }

    public static VersusDefeated villainDefeatedRandom() {
        return villainDefeatedValues[RANDOM.nextInt(villainDefeatedValues.length)];
    }

    public static VersusDefeated onlyHeroDefeated() {
        return VersusDefeated.HERO;
    }

    public static VersusDefeated onlyVillainDefeated() {
        return VersusDefeated.VILLAIN;
    }

    public static VersusDefeated noneDefeated() {
        return VersusDefeated.NONE;
    }
}
