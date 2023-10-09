package com.example.heroes.villains.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class VillainPowerInvalidLengthException extends DomainError {

    public VillainPowerInvalidLengthException() {
        super("villain_power_invalid_length", "The villain power length must be between 1 and 30 letters");
    }
}
