package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.Identifier;

public final class VillainId extends Identifier {

    private static final String AGGREGATE_ROOT_NAME = "villain";

    public VillainId(String value) {
        super(value, AGGREGATE_ROOT_NAME);
    }

}
