package com.example.heroes.versus.application.find;

import com.example.heroes.versus.domain.Versus;

public record VersusResponse(String id, String heroId, String villainId, String defeated) {

    public static VersusResponse fromAggregate(Versus versus) {
        return new VersusResponse(versus.id().value(),
                versus.heroId().value(),
                versus.villainid().value(),
                versus.defeated().value());
    }
}
