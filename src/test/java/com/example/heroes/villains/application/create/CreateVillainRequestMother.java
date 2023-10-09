package com.example.heroes.villains.application.create;

import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainIdMother;
import com.example.heroes.villains.domain.VillainNameMother;
import com.example.heroes.villains.domain.VillainPowerMother;

public final class CreateVillainRequestMother {

    public static CreateVillainRequest create(String id, String name, String power) {
        return new CreateVillainRequest(id, name, power);
    }

    public static CreateVillainRequest random() {
        return new CreateVillainRequest(VillainIdMother.randomValue(), VillainNameMother.random(), VillainPowerMother.random());
    }

    public static CreateVillainRequest fromAggregate(Villain villain) {
        return new CreateVillainRequest(villain.getId().value(), villain.getName().value(), villain.getPower().value());
    }
}
