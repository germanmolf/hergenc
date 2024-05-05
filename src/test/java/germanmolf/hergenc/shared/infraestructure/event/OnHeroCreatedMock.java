package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.heroes.domain.HeroCreatedEvent;
import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;

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
