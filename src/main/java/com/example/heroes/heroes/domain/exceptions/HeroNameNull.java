package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class HeroNameNull extends DomainError {

    public HeroNameNull() {
        super("hero_name_null", "The hero name is null");
    }
}
