package com.example.heroes.versus.domain;

import com.example.heroes.versus.domain.exceptions.VersusDefeatedInvalidValueException;
import com.example.heroes.versus.domain.exceptions.VersusDefeatedNullException;

public enum VersusDefeated {

    HERO("hero"),
    VILLAIN("villain"),
    BOTH("both"),
    NONE("none");

    private final String defeated;

    VersusDefeated(String defeated) {
        this.defeated = defeated;
    }

    public static VersusDefeated fromValue(String value) {
        if (value == null) {
            throw new VersusDefeatedNullException();
        }
        return switch (value) {
            case "hero" -> HERO;
            case "villain" -> VILLAIN;
            case "both" -> BOTH;
            case "none" -> NONE;
            default -> throw new VersusDefeatedInvalidValueException(value);
        };
    }

    public String value() {
        return defeated;
    }
}
