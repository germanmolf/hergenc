package com.example.heroes.villains.application.find;

import com.example.heroes.villains.domain.Villain;

public final class VillainResponseMother {

    public static VillainResponse fromAggregate(Villain villain) {
        return new VillainResponse(villain.getId().value(), villain.getName().value(), villain.getPower().value());
    }
}
