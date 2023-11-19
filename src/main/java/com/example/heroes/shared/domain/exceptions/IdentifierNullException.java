package com.example.heroes.shared.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class IdentifierNullException extends DomainError {

    public IdentifierNullException(String aggregateRootName) {
        super(String.format("%s_identifier_null", aggregateRootName),
                String.format("The %s identifier is null", aggregateRootName));
    }
}
