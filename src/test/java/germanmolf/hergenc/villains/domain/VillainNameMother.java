package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.WordMother;

public final class VillainNameMother {

    private static VillainName random() {
        return new VillainName(WordMother.randomMinMax(1, 20));
    }

    public static String randomValue() {
        return random().value();
    }
}
