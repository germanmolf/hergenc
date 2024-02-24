package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;

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
