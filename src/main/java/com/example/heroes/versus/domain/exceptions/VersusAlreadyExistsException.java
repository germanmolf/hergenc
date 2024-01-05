package com.example.heroes.versus.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.versus.domain.VersusId;

public final class VersusAlreadyExistsException extends DomainError {

    public VersusAlreadyExistsException(VersusId id) {
        super("versus_already_exists", "The versus <%s> already exists".formatted(id.value()));
    }
}
