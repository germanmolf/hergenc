package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.Identifier;

public final class VillainId extends Identifier {

    private static final String AGGREGATE_ROOT_NAME = "villain";

    public VillainId(String value) {
        super(value, AGGREGATE_ROOT_NAME);
    }

}
