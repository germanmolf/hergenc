package com.example.heroes.shared.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class IdentifierNullException extends DomainError {

    public IdentifierNullException() {
        super("hero_id_null", "The hero id is null");
    }
}
