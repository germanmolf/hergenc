package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.shared.domain.Identifier;

public final class HeroId extends Identifier {

    private static final String AGGREGATE_ROOT_NAME = "hero";

    public HeroId(String value) {
        super(value, AGGREGATE_ROOT_NAME);
    }
}