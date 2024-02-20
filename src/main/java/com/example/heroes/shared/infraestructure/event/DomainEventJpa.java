package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;
import com.example.heroes.shared.infraestructure.persistence.MapToJsonConverter;
import com.example.heroes.shared.infraestructure.persistence.SetToJsonConverter;
import jakarta.persistence.*;
import lombok.*;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Getter
@Setter
@Entity
@Table(name = "domain_event")
public final class DomainEventJpa {

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

    public static DomainEventJpa fromDomainEvent(DomainEvent event, Set<String> subscribers) {
        return new DomainEventJpa(event.eventId(), event.aggregateId(), event.occurredOn(), event.eventName(),
                subscribers, event.toPrimitives());
    }

}
