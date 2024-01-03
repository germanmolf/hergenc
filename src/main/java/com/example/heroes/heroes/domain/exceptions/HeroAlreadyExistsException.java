package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.DomainError;

public final class HeroAlreadyExistsException extends DomainError {

    public HeroAlreadyExistsException(HeroId id) {
        super("hero_already_exists", "The hero <%s> already exists".formatted(id.value()));
    }
}
