package com.example.heroes.versus.domain.exceptions;

import com.example.heroes.shared.domain.DomainError;

public final class VersusDefeatedNullException extends DomainError {

    public VersusDefeatedNullException() {
        super("versus_defeated_null", "The versus defeated is null");
    }
}
