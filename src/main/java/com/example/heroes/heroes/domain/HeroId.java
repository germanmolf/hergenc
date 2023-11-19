package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.Identifier;

public final class HeroId extends Identifier {

    private static final String AGGREGATE_ROOT_NAME = "hero";

    public HeroId(String value) {
        super(value, AGGREGATE_ROOT_NAME);
    }
}