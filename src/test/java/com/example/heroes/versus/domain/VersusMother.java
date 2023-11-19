package com.example.heroes.versus.domain;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.versus.application.create.CreateVersusRequest;

public final class VersusMother {

    private static Versus create(String id, String heroId, String villainId, String defeated) {
        return new Versus(id, heroId, villainId, defeated);
    }

    public static Versus random() {
        return create(VersusIdMother.random().value(),
                HeroIdMother.random().value(),
                VersusIdMother.random().value(),
                VersusDefeatedMother.random().value());
    }

    public static Versus create(String heroId, String villainId, String defeated) {
        return create(VersusIdMother.random().value(), heroId, villainId, defeated);
    }

    public static Versus fromRequest(CreateVersusRequest request) {
        return create(request.id(), request.heroId(), request.villainId(), request.defeated());
    }
}
