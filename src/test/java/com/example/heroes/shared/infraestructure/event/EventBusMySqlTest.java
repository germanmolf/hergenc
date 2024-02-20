package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.heroes.application.update.UpdateHeroOnVersusCreated;
import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.heroes.domain.HeroCreatedEventMother;
import com.example.heroes.shared.infraestructure.persistence.IntegrationTestModule;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusCreatedEventMother;
import com.example.heroes.villains.application.update.UpdateVillainOnVersusCreated;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class EventBusMySqlTest extends IntegrationTestModule {

    private final int MILLIS_TO_WAIT = 1000;
    @Autowired
    private EventBusMySql eventBus;
    @Autowired
    private DomainEventRepositoryMySql repository;
    @Autowired
    private HeroCreatedEventSubscriberMock subscriber;
    @Autowired
    private UpdateHeroOnVersusCreated versusSubscriber1;
    @Autowired
    private UpdateVillainOnVersusCreated versusSubscriber2;
    @Autowired
    private MySqlDomainEventsConsumer consumer;

    @Test
    void publish_an_event() {
        HeroCreatedEvent event = HeroCreatedEventMother.random();
        DomainEventJpa eventPublished = DomainEventJpa.fromDomainEvent(event, Set.of(subscriber.subscriberName()));

        eventBus.publish(List.of(event));
        List<DomainEventJpa> result = repository.searchAll();

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    @Test
    void publish_an_event_for_every_subscriber() {
        VersusCreatedEvent event = VersusCreatedEventMother.random();
        DomainEventJpa eventPublished = DomainEventJpa.fromDomainEvent(event,
                Set.of(versusSubscriber1.subscriberName(), versusSubscriber2.subscriberName()));

        eventBus.publish(List.of(event));
        List<DomainEventJpa> result = repository.searchAll();

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    //consume an event
    @Test
    void consume_an_event() throws InterruptedException {
        HeroCreatedEvent event = HeroCreatedEventMother.random();
        eventBus.publish(List.of(event));
        Thread consumerProcess = new Thread(() -> consumer.consume());

        consumerProcess.start();
        Thread.sleep(MILLIS_TO_WAIT);

        assertTrue(subscriber.hasBeenExecuted);
    }

}
