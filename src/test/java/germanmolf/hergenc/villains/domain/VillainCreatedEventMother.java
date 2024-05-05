package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.villains.application.create.CreateVillainRequest;

public final class VillainCreatedEventMother {

    private static VillainCreatedEvent create(String id, String name, String power) {
        return new VillainCreatedEvent(id, name, power);
    }

    public static VillainCreatedEvent fromAggregate(Villain villain) {
        return create(villain.id().value(), villain.name().value(), villain.power().value());
    }

    public static VillainCreatedEvent fromRequest(CreateVillainRequest request) {
        return create(request.id(), request.name(), request.power());
    }

    public static VillainCreatedEvent random() {
        return create(VillainIdMother.randomValue(), VillainNameMother.randomValue(), VillainPowerMother.randomValue());
    }
}
