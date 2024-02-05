package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;

@Injectable
public final class HeroCreatedEventSubscriberMock implements DomainEventSubscriber<HeroCreatedEvent> {

    @Override
    public void on(HeroCreatedEvent event) {
        return;
    }

    @Override
    public String subscriberName() {
        return "subscriber.mock";
    }

}
