package com.example.heroes.versus.domain;

import com.example.heroes.versus.application.create.CreateVersusRequest;

public final class VersusMother {

    public static Versus fromRequest(CreateVersusRequest request) {
        return new Versus(request.id(), request.heroId(), request.villainId(), request.defeated());
    }
}
