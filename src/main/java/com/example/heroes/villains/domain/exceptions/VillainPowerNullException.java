package com.example.heroes.villains.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class VillainPowerNullException extends DomainError {

    public VillainPowerNullException() {
        super("villain_power_null", "The villain power is null");
    }
}
