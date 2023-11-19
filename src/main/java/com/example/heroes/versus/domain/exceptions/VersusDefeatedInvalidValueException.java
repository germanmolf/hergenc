package com.example.heroes.versus.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class VersusDefeatedInvalidValueException extends DomainError {

    public VersusDefeatedInvalidValueException(String value) {
        super("versus_defeated_invalid_value", String.format("The versus defeated value <%s> is not valid", value));
    }
}
