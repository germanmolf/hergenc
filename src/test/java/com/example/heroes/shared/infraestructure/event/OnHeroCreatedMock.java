package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;

@Injectable
public final class OnHeroCreatedMock implements DomainEventSubscriber<HeroCreatedEvent> {

    public Boolean hasBeenExecuted = false;

    @Override
    public void on(HeroCreatedEvent event) {
        hasBeenExecuted = true;
    }

    @Override
    public String subscriberName() {
        return "hero-subscriber.mock";
    }

}
