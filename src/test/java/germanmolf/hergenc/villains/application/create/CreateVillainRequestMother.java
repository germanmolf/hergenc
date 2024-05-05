package germanmolf.hergenc.villains.application.create;

import germanmolf.hergenc.shared.domain.WordMother;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import germanmolf.hergenc.villains.domain.VillainNameMother;
import germanmolf.hergenc.villains.domain.VillainPowerMother;
import germanmolf.hergenc.villains.domain.Villain;
import org.apache.logging.log4j.util.Strings;

public final class CreateVillainRequestMother {

    private static CreateVillainRequest create(String id, String name, String power) {
        return new CreateVillainRequest(id, name, power);
    }

    public static CreateVillainRequest random() {
        return create(VillainIdMother.randomValue(), VillainNameMother.randomValue(), VillainPowerMother.randomValue());
    }

    public static CreateVillainRequest fromAggregate(Villain villain) {
        return create(villain.id().value(), villain.name().value(), villain.power().value());
    }

    public static CreateVillainRequest withIdNull() {
        return create(null, VillainNameMother.randomValue(), VillainPowerMother.randomValue());
    }

    public static CreateVillainRequest withIdNotValid() {
        return create("qwe", VillainNameMother.randomValue(), VillainPowerMother.randomValue());
    }

    public static CreateVillainRequest withNameNull() {
        return create(VillainIdMother.randomValue(), null, VillainPowerMother.randomValue());
    }

    public static CreateVillainRequest withNameBlank() {
        return create(VillainIdMother.randomValue(), Strings.EMPTY, VillainPowerMother.randomValue());
    }

    public static CreateVillainRequest withNameOverLength() {
        return create(VillainIdMother.randomValue(), WordMother.randomMin(21), VillainPowerMother.randomValue());
    }

    public static CreateVillainRequest withPowerNull() {
        return create(VillainIdMother.randomValue(), VillainNameMother.randomValue(), null);
    }

    public static CreateVillainRequest withPowerBlank() {
        return create(VillainIdMother.randomValue(), VillainNameMother.randomValue(), Strings.EMPTY);
    }

    public static CreateVillainRequest withPowerOverLength() {
        return create(VillainIdMother.randomValue(), VillainNameMother.randomValue(), WordMother.randomMin(31));
    }
}
