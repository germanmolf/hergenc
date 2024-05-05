package germanmolf.hergenc.versus.application.find;

import germanmolf.hergenc.versus.domain.Versus;

public record VersusResponse(String id, String heroId, String villainId, String defeated) {

    public static VersusResponse fromAggregate(Versus versus) {
        return new VersusResponse(versus.id().value(),
                versus.heroId().value(),
                versus.villainid().value(),
                versus.defeated().value());
    }
}
