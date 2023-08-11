package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class HeroPowerNullException extends DomainError {

    public HeroPowerNullException() {
        super("hero_power_null", "The hero power is null");
    }
}
