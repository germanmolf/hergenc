package com.example.heroes.villains.application.create;

import com.example.heroes.shared.domain.WordMother;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainIdMother;
import com.example.heroes.villains.domain.VillainNameMother;
import com.example.heroes.villains.domain.VillainPowerMother;
import org.apache.logging.log4j.util.Strings;

public final class CreateVillainRequestMother {

    private static CreateVillainRequest create(String id, String name, String power) {
        return new CreateVillainRequest(id, name, power);
    }

    public static CreateVillainRequest random() {
        return create(VillainIdMother.random().value(), VillainNameMother.random().value(), VillainPowerMother.random().value());
    }

    public static CreateVillainRequest fromAggregate(Villain villain) {
        return create(villain.id().value(), villain.name().value(), villain.power().value());
    }

    public static CreateVillainRequest withIdNull() {
        return create(null, VillainNameMother.random().value(), VillainPowerMother.random().value());
    }

    public static CreateVillainRequest withIdNotValid() {
        return create("qwe", VillainNameMother.random().value(), VillainPowerMother.random().value());
    }

    public static CreateVillainRequest withNameNull() {
        return create(VillainIdMother.random().value(), null, VillainPowerMother.random().value());
    }

    public static CreateVillainRequest withNameBlank() {
        return create(VillainIdMother.random().value(), Strings.EMPTY, VillainPowerMother.random().value());
    }

    public static CreateVillainRequest withNameOverLength() {
        return create(VillainIdMother.random().value(), WordMother.randomMin(21), VillainPowerMother.random().value());
    }

    public static CreateVillainRequest withPowerNull() {
        return create(VillainIdMother.random().value(), VillainNameMother.random().value(), null);
    }

    public static CreateVillainRequest withPowerBlank() {
        return create(VillainIdMother.random().value(), VillainNameMother.random().value(), Strings.EMPTY);
    }

    public static CreateVillainRequest withPowerOverLength() {
        return create(VillainIdMother.random().value(), VillainNameMother.random().value(), WordMother.randomMin(31));
    }
}
