package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.domain.exceptions.HeroIdNull;
import com.example.heroes.shared.Identifier;

public final class HeroId extends Identifier {

    public HeroId(String value) {
        super(value);
        checkIsNotNull(value);
    }

    private void checkIsNotNull(String value) throws HeroIdNull {
        if (value == null) throw new HeroIdNull();
    }
}