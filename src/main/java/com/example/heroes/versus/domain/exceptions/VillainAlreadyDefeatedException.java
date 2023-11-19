package com.example.heroes.versus.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.villains.domain.VillainId;

public final class VillainAlreadyDefeatedException extends DomainError {

    public VillainAlreadyDefeatedException(VillainId id) {
        super("villain_already_defeated", String.format("The villain <%s> is already defeated", id.value()));
    }
}
