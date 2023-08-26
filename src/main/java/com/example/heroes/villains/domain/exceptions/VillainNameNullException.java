package com.example.heroes.villains.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class VillainNameNullException extends DomainError {

    public VillainNameNullException() {
        super("villain_name_null", "The villain name is null");
    }
}
