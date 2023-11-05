package com.example.heroes.shared.domain;

import java.util.Random;

public final class LongMother {

    public static Long greaterThanZero() {
        return new Random().nextLong(0L, Long.MAX_VALUE);
    }
}
