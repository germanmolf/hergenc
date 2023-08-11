package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.DomainError;

public final class HeroNotFoundException extends DomainError {

    public HeroNotFoundException(HeroId id) {
        super("hero_not_exist", String.format("The hero <%s> doesn't exist", id.value()));
    }
}
