package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "domain_event")
public final class DomainEventJpa {

    @Id
    private String eventId;
    private String aggregateId;
    private String occurredOn;
    private String name;
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
    private Set<String> subscribers;
    @Column(columnDefinition = "json")
    private Map<String, String> body;

    public static DomainEventJpa fromDomainEvent(DomainEvent event, Set<String> subscribers) {
        return new DomainEventJpa(event.eventId(), event.aggregateId(), event.occurredOn(), event.eventName(),
                subscribers, event.toPrimitives());
    }

}
