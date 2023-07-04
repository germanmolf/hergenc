package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class HeroIdNull extends DomainError {

    public HeroIdNull() {
        super("hero_id_null", "The hero id is null");
    }
}
