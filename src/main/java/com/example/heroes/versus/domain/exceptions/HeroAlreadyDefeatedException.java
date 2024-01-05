package com.example.heroes.versus.domain.exceptions;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.DomainError;

public final class HeroAlreadyDefeatedException extends DomainError {

    public HeroAlreadyDefeatedException(HeroId id) {
        super("hero_already_defeated", "The hero <%s> is already defeated".formatted(id.value()));
    }
}
