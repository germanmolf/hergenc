package com.example.heroes.villains.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.villains.domain.VillainId;

public final class VillainNotFoundException extends DomainError {

    public VillainNotFoundException(VillainId id) {
        super("villain_not_found", String.format("Not found villain <%s>", id.value()));
    }
}
