package com.example.heroes.versus.application.find;

import com.example.heroes.versus.domain.Versus;

public final class VersusResponseMother {

    public static VersusResponse fromAggregate(Versus versus) {
        return new VersusResponse(versus.getId().value(),
                versus.getHeroId().value(),
                versus.getVillainId().value(),
                versus.getDefeated().value());
    }
}
