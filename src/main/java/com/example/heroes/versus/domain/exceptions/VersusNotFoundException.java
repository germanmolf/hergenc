package com.example.heroes.versus.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.versus.domain.VersusId;

public final class VersusNotFoundException extends DomainError {

    public VersusNotFoundException(VersusId id) {
        super("versus_not_found", String.format("Not found versus <%s>", id.value()));
    }
}
