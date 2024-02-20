package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.heroes.domain.HeroCreatedEventMother;
import com.example.heroes.shared.infraestructure.persistence.IntegrationTestModule;
import com.example.heroes.villains.domain.VillainCreatedEvent;
import com.example.heroes.villains.domain.VillainCreatedEventMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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
    private OnHeroCreatedMock subscriber;
    @Autowired
    private MySqlDomainEventsConsumer consumer;

    @Test
    void publish_an_event() {
        HeroCreatedEvent event = HeroCreatedEventMother.random();
        EventQueued eventPublished = EventQueuedMother.fromDomainEvent(event);

        eventBus.publish(List.of(event));
        List<EventQueued> result = repository.searchAll();

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    @Test
    void publish_an_event_for_every_subscriber() {
        VillainCreatedEvent event = VillainCreatedEventMother.random();
        EventQueued eventPublished = EventQueuedMother.fromDomainEvent(event);

        eventBus.publish(List.of(event));
        List<EventQueued> result = repository.searchAll();

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    @Test
    void consume_an_event() throws InterruptedException {
        HeroCreatedEvent event = HeroCreatedEventMother.random();
        eventBus.publish(List.of(event));
        Thread consumerProcess = new Thread(() -> consumer.consume());

        consumerProcess.start();
        Thread.sleep(MILLIS_TO_WAIT);
        consumer.stop();

        assertTrue(subscriber.hasBeenExecuted);
    }

    @Test
    void delete_event_consumed_by_all_subscribers() throws InterruptedException {
        HeroCreatedEvent event = HeroCreatedEventMother.random();
        eventBus.publish(List.of(event));
        Thread consumerProcess = new Thread(() -> consumer.consume());

        consumerProcess.start();
        Thread.sleep(MILLIS_TO_WAIT);
        consumer.stop();
        Thread.sleep(MILLIS_TO_WAIT);
        List<EventQueued> result = repository.searchAll();

        assertTrue(result.isEmpty());
    }

    @Test
    void update_event_with_failed_call() throws InterruptedException {
        VillainCreatedEvent event = VillainCreatedEventMother.random();
        EventQueued eventPublished = EventQueuedMother.withSubscribers(event, "villain-subscriber.mock-fails");
        eventBus.publish(List.of(event));
        Thread consumerProcess = new Thread(() -> consumer.consume());

        consumerProcess.start();
        Thread.sleep(MILLIS_TO_WAIT);
        consumer.stop();
        Thread.sleep(MILLIS_TO_WAIT);
        List<EventQueued> result = repository.searchAll();

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    //hacer una dead letter

}