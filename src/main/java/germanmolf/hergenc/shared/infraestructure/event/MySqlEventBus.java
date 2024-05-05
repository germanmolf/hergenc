package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.shared.domain.event.DomainEvent;
import germanmolf.hergenc.shared.domain.event.EventBus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public final class MySqlEventBus implements EventBus {

    private final MySqlDomainEventRepository repository;
    private final DomainEventSubscribersInformation domainEventSubscribersInformation;

    public MySqlEventBus(MySqlDomainEventRepository repository, DomainEventSubscribersInformation domainEventSubscribersInformation) {
        this.repository = repository;
        this.domainEventSubscribersInformation = domainEventSubscribersInformation;
    }

    @Override
    public void publish(List<DomainEvent> events) {
        events.forEach(this::publish);
    }

    private void publish(DomainEvent event) {
        Set<String> subscribersNames =
                domainEventSubscribersInformation.getDomainEventSubscribersNames(event.eventName());
        EventQueued eventQueued = EventQueued.fromDomainEvent(event, subscribersNames);
        if (eventQueued.hasSubscribers()) {
            repository.save(eventQueued);
        }
    }

}
