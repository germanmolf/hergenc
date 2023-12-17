package com.example.heroes.villains.application.find;

import com.example.heroes.villains.domain.Villain;

public record VillainResponse(String id, String name, String power) {

    public static VillainResponse fromAggregate(Villain villain) {
        return new VillainResponse(villain.id().value(), villain.name().value(), villain.power().value());
    }

}
