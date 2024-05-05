package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.shared.domain.event.DomainEvent;
import germanmolf.hergenc.shared.infraestructure.persistence.MapToJsonConverter;
import germanmolf.hergenc.shared.infraestructure.persistence.SetToJsonConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "event_queued")
public final class EventQueued {

    @Id
    private String id;
    private String aggregateId;
    private String occurredOn;
    private String name;
    @Column(columnDefinition = "json")
    @Convert(converter = SetToJsonConverter.class)
    private Set<String> subscribers;
    @Column(columnDefinition = "json")
    @Convert(converter = MapToJsonConverter.class)
    private Map<String, String> body;

    public static EventQueued fromDomainEvent(DomainEvent event, Set<String> subscribers) {
        return new EventQueued(event.eventId(), event.aggregateId(), event.occurredOn(), event.eventName(),
                subscribers, event.toPrimitives());
    }

    public boolean hasSubscribers() {
        return !subscribers.isEmpty();
    }
}
