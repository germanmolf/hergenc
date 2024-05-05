package germanmolf.hergenc.shared.domain.event;

import java.util.List;

public interface EventBus {

    void publish(List<DomainEvent> events);

}
