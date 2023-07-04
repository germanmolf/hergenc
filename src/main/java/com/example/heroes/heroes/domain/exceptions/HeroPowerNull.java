package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class HeroPowerNull extends DomainError {

    public HeroPowerNull() {
        super("hero_power_null", "The hero power is null");
    }
}
