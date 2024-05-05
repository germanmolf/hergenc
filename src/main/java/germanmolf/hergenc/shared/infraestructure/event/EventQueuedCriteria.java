package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.shared.domain.criteria.Criteria;

public final class EventQueuedCriteria {

    public static Criteria firstTheOldestOnes() {
        return new Criteria().setOrder("occurredOn", "asc");
    }

}
