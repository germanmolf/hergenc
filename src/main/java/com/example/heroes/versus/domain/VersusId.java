package com.example.heroes.versus.domain;

import com.example.heroes.shared.domain.Identifier;

public final class VersusId extends Identifier {

    private static final String AGGREGATE_ROOT_NAME = "versus";

    public VersusId(String value) {
        super(value, AGGREGATE_ROOT_NAME);
    }
}
