package com.example.heroes.villains.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;
import com.example.heroes.villains.domain.VillainId;

public final class VillainAlreadyExistsException extends DomainError {

    public VillainAlreadyExistsException(VillainId id) {
        super("villain_already_exists", String.format("The villain <%s> already exists", id.value()));
    }
}
