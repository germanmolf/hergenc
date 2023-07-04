package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class HeroPowerInvalidLength extends DomainError {

    public HeroPowerInvalidLength() {
        super("hero_power_invalid_length", "The hero power length must be between 1 and 30 letters");
    }
}
