package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.heroes.domain.HeroCreatedEvent;
import germanmolf.hergenc.heroes.domain.HeroCreatedEventMother;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;
import germanmolf.hergenc.shared.infraestructure.persistence.IntegrationTestModule;
import germanmolf.hergenc.villains.domain.VillainCreatedEventMother;
import germanmolf.hergenc.villains.domain.VillainCreatedEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class MySqlEventBusTest extends IntegrationTestModule {

    private static final int MILLIS_TO_WAIT = 1000;
    private static final Criteria firstTheOldestOnesCriteria = EventQueuedCriteria.firstTheOldestOnes();
    private OnHeroCreatedMock subscriber;
    @Autowired
    private MySqlEventBus eventBus;
    @Autowired
    private MySqlDomainEventRepository repository;
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
        Thread.sleep(MILLIS_TO_WAIT);

        assertTrue(subscriber.hasBeenExecuted);
    }

    @Test
    void delete_event_consumed_by_all_subscribers() throws InterruptedException {
        HeroCreatedEvent event = HeroCreatedEventMother.random();

        eventBus.publish(List.of(event));
        Thread.sleep(MILLIS_TO_WAIT);
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

        assertTrue(result.isEmpty());
    }

    @Test
    void update_event_with_failed_call() throws InterruptedException {
        VillainCreatedEvent event = VillainCreatedEventMother.random();
        EventQueued eventPublished = EventQueuedMother.withSubscribers(event, "villain-subscriber.mock-fails");

        eventBus.publish(List.of(event));
        Thread.sleep(MILLIS_TO_WAIT);
        List<EventQueued> result = repository.search(firstTheOldestOnesCriteria);

        assertThat(List.of(eventPublished), containsInAnyOrder(result.toArray()));
    }

}
