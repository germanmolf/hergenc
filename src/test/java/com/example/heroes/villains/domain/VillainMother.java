package com.example.heroes.villains.domain;

import com.example.heroes.villains.application.create.CreateVillainRequest;

public final class VillainMother {

    public static Villain random() {
        return new Villain(VillainIdMother.randomValue(), VillainNameMother.random());
    }

    public static Villain fromRequest(CreateVillainRequest request) {
        return new Villain(request.getId(), request.getName());
    }
}
