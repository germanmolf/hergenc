package germanmolf.hergenc.shared.domain;

import java.util.Random;

public final class IntegerMother {

    public static final Random RANDOM = new Random();

    public static Integer randomPositive() {
        return RANDOM.nextInt(Integer.MAX_VALUE);
    }
}
