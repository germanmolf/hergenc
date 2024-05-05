package germanmolf.hergenc.versus.application.find;

import germanmolf.hergenc.versus.application.find.VersusResponse;
import germanmolf.hergenc.versus.domain.Versus;

public final class VersusResponseMother {

    public static VersusResponse fromAggregate(Versus versus) {
        return new VersusResponse(versus.id().value(),
                versus.heroId().value(),
                versus.villainid().value(),
                versus.defeated().value());
    }
}
