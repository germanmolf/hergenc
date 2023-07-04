package com.example.heroes.heroes.domain.exceptions;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.DomainError;

public final class HeroNotExist extends DomainError {

    public HeroNotExist(HeroId id) {
        super("hero_not-exist", String.format("The hero <%s> doesn't exist", id.value()));
    }
}
