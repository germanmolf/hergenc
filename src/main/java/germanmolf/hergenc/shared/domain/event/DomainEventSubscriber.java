package germanmolf.hergenc.shared.domain.event;

public interface DomainEventSubscriber<T extends DomainEvent> {

    void on(T event);

    String subscriberName();

}
