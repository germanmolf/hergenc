package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.WordMother;

public final class VillainPowerMother {

    private static VillainPower random() {
        return new VillainPower(WordMother.randomMinMax(1, 30));
    }

    public static String randomValue() {
        return random().value();
    }
}
