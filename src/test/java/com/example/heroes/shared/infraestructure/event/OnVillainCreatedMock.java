package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;
import com.example.heroes.villains.domain.VillainCreatedEvent;

@Injectable
public final class OnVillainCreatedMock implements DomainEventSubscriber<VillainCreatedEvent> {

    public Boolean hasBeenExecuted = false;

    @Override
    public void on(VillainCreatedEvent event) {
        hasBeenExecuted = true;
    }

    @Override
    public String subscriberName() {
        return "villain-subscriber.mock";
    }

}
