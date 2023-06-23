package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.DomainError;

public final class HeroNameInvalidLength extends DomainError {

    public HeroNameInvalidLength() {
        super("hero_name_invalid_length", "The hero name length must be between 1 and 20 letters");
    }
}
