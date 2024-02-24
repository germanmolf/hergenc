package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.heroes.domain.HeroCreatedEventMother;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;
import com.example.heroes.shared.infraestructure.persistence.IntegrationTestModule;
import com.example.heroes.villains.domain.VillainCreatedEvent;
import com.example.heroes.villains.domain.VillainCreatedEventMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class EventBusMySqlTest extends IntegrationTestModule {

    private static final int MILLIS_TO_WAIT = 1000;
    private static final Criteria firstTheOldestOnesCriteria = EventQueuedCriteria.firstTheOldestOnes();
    private OnHeroCreatedMock subscriber;
    @Autowired
    private EventBusMySql eventBus;
    @Autowired
    private DomainEventRepositoryMySql repository;
    @Autowired
    private MySqlDomainEventsConsumer consumer;

    @BeforeEach
    void setUp() {
        subscriber = new OnHeroCreatedMock();
        consumer.withSubscribersInformation(
                Map.of(
                        "hero.created", Set.of("hero-subscriber.mock"),
                        "villain.created", Set.of("villain-subscriber.mock", "villain-subscriber.mock-fails")
                ),
                Map.of(
                        "hero.created", HeroCreatedEvent.class,
                        "villain.created", VillainCreatedEvent.class),
                Map.of(
                        "hero-subscriber.mock", (DomainEventSubscriber) subscriber,
                        "villain-subscriber.mock", (DomainEventSubscriber) new OnVillainCreatedMock())
        );
    }

    @Test
    void publish_an_event() {
        HeroCreatedEvent event = HeroCreatedEventMother.random();
        EventQueued eventPublished = EventQueuedMother.fromDomainEvent(event);

        eventBus.publish(List.of(event));
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    @Test
    void publish_an_event_for_every_subscriber() {
        VillainCreatedEvent event = VillainCreatedEventMother.random();
        EventQueued eventPublished = EventQueuedMother.fromDomainEvent(event);

        eventBus.publish(List.of(event));
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

    @Test
    void no_publish_an_event_with_no_subscribers() {
        OnMockEvent event = new OnMockEvent();

        eventBus.publish(List.of(event));
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

        assertTrue(result.isEmpty());
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
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

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
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

}
