package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;
import com.example.heroes.villains.domain.VillainCreatedEvent;

@Injectable
public final class OnVillainCreatedFailsMock implements DomainEventSubscriber<VillainCreatedEvent> {

    @Override
    public void on(VillainCreatedEvent event) {
        throw new RuntimeException("test");
    }

    @Override
    public String subscriberName() {
        return "villain-subscriber.mock-fails";
    }

}
