package com.example.heroes.shared.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class IdentifierNotValid extends DomainError {

    public IdentifierNotValid(String id) {
        super("identifier_not_valid", String.format("The identifier <%s> is not a valid UUID", id));
    }
}
