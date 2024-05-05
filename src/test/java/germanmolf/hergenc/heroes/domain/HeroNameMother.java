package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.shared.domain.WordMother;

public final class HeroNameMother {

    private static HeroName random() {
        return new HeroName(WordMother.randomMinMax(1, 20));
    }

    public static String randomValue() {
        return random().value();
    }
}
