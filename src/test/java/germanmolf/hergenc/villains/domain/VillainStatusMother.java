package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.villains.domain.VillainStatus;

import java.util.Random;

public final class VillainStatusMother {

    private final static Random RANDOM = new Random();
    private final static VillainStatus[] statusValues = VillainStatus.values();

    public static VillainStatus random() {
        return statusValues[RANDOM.nextInt(statusValues.length)];
    }

    public static VillainStatus defeated() {
        return VillainStatus.DEFEATED;
    }

    public static VillainStatus active() {
        return VillainStatus.ACTIVE;
    }

}
