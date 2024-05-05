package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.shared.domain.UuidMother;

public final class HeroIdMother {

    public static HeroId random() {
        return new HeroId(UuidMother.random());
    }

    public static String randomValue() {
        return random().value();
    }

    public static HeroId create(String id) {
        return new HeroId(id);
    }
}
