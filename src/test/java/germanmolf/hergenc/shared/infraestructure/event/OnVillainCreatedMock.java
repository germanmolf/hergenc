package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;
import germanmolf.hergenc.villains.domain.VillainCreatedEvent;

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
