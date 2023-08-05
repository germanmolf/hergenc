package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.DomainError;

public final class HeroAlreadyExists extends DomainError {

    public HeroAlreadyExists(HeroId id) {
        super("hero_already_exists", String.format("The hero <%s> already exists", id.value()));
    }
}
