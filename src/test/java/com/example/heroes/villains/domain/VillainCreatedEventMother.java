package com.example.heroes.villains.domain;

import com.example.heroes.villains.application.create.CreateVillainRequest;

public final class VillainCreatedEventMother {

    private static VillainCreatedEvent create(String id, String name, String power) {
        return new VillainCreatedEvent(id, name, power);
    }

    public static VillainCreatedEvent fromAggregate(Villain villain) {
        return create(villain.getId().value(), villain.getName().value(), villain.getPower().value());
    }

    public static VillainCreatedEvent fromRequest(CreateVillainRequest request) {
        return create(request.getId(), request.getName(), request.getPower());
    }
}
