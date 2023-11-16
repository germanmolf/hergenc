package com.example.heroes.versus.application.create.find;

import com.example.heroes.versus.domain.Versus;

public record VersusResponse(String id, String heroId, String villainId, String defeated) {

    public static VersusResponse fromAggregate(Versus versus) {
        return new VersusResponse(versus.getId().value(),
                versus.getHeroId().value(),
                versus.getVillainId().value(),
                versus.getDefeated().value());
    }
}
