package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;
import germanmolf.hergenc.villains.domain.VillainCreatedEvent;

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
