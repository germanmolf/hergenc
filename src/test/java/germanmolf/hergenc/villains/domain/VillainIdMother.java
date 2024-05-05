package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.UuidMother;

public final class VillainIdMother {

    public static VillainId random() {
        return new VillainId(UuidMother.random());
    }

    public static String randomValue() {
        return random().value();
    }

    public static VillainId create(String id) {
        return new VillainId(id);
    }
}
