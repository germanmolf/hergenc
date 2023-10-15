package com.example.heroes.villains.domain;

import com.example.heroes.villains.application.create.CreateVillainRequest;

public final class VillainCreatedEventMother {

    public static VillainCreatedEvent fromAggregate(Villain villain) {
        return new VillainCreatedEvent(villain.getId().value(), villain.getName().value());
    }

    public static VillainCreatedEvent fromRequest(CreateVillainRequest request) {
        return new VillainCreatedEvent(request.getId(), request.getName());
    }
}
