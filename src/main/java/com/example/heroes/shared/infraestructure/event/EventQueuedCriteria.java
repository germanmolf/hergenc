package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.criteria.Criteria;

public final class EventQueuedCriteria {

    public static Criteria firstTheOldestOnes() {
        return new Criteria().setOrder("occurredOn", "asc");
    }

}
