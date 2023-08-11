package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class HeroNameNullException extends DomainError {

    public HeroNameNullException() {
        super("hero_name_null", "The hero name is null");
    }
}
