package com.example.heroes.villains.application.create;

import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainIdMother;
import com.example.heroes.villains.domain.VillainNameMother;

public final class CreateVillainRequestMother {

    public static CreateVillainRequest random() {
        return new CreateVillainRequest(VillainIdMother.randomValue(), VillainNameMother.random());
    }

    public static CreateVillainRequest fromAggregate(Villain villain) {
        return new CreateVillainRequest(villain.getId().value(), villain.getName().value());
    }

    public static CreateVillainRequest withName(String name) {
        return new CreateVillainRequest(VillainIdMother.randomValue(), name);
    }
}
