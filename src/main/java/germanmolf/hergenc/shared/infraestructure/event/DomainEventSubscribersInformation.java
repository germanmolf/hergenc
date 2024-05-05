package germanmolf.hergenc.shared.infraestructure.event;

import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.event.DomainEvent;
import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.reflections.util.ReflectionUtilsPredicates.withAnnotation;

@Service
public final class DomainEventSubscribersInformation {

    private final ApplicationContext context;
    private Map<String, Set<String>> subscribersOfEvents;
    private Map<String, Class<? extends DomainEvent>> eventsClasses;
    private Map<String, DomainEventSubscriber<DomainEvent>> subscribers;

    public DomainEventSubscribersInformation(ApplicationContext context) throws ReflectiveOperationException {
        this.context = context;
        subscribersOfEvents = new HashMap<>();
        eventsClasses = new HashMap<>();
        subscribers = new HashMap<>();
        scanDomainEventSubscribers();
    }

    public Set<String> getDomainEventSubscribersNames(String eventName) {
        return subscribersOfEvents.getOrDefault(eventName, new HashSet<>());
    }

    private void scanDomainEventSubscribers() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("germanmolf.hergenc");
        var subscribersClasses = reflections.getSubTypesOf(DomainEventSubscriber.class).stream()
                .filter(withAnnotation(Injectable.class))
                .toList();

        for (var subscriberClass : subscribersClasses) {
            DomainEventSubscriber<DomainEvent> subscriber = context.getBean(subscriberClass);
            String subscriberName = subscriber.subscriberName();
            subscribers.put(subscriberName, subscriber);

            Class<DomainEvent> eventClass = getEventClass(subscriberClass);
            DomainEvent event = eventClass.getConstructor().newInstance();
            String eventName = event.eventName();
            eventsClasses.put(eventName, eventClass);

            Set<String> eventSubscribersNames = subscribersOfEvents.getOrDefault(eventName, new HashSet<>());
            eventSubscribersNames.add(subscriberName);
            subscribersOfEvents.put(eventName, eventSubscribersNames);
        }
    }

    public Class<? extends DomainEvent> getEventClass(String eventName) {
        return eventsClasses.get(eventName);
    }

    public DomainEventSubscriber<DomainEvent> getSubscriber(String subscriberName) {
        return subscribers.get(subscriberName);
    }

    private Class<DomainEvent> getEventClass(Class<?> subscriberClass) {
        ParameterizedType genericInterface = (ParameterizedType) subscriberClass.getGenericInterfaces()[0];
        return (Class<DomainEvent>) genericInterface.getActualTypeArguments()[0];
    }

    public void withInformation(Map<String, Set<String>> subscribersOfEvents,
                                Map<String, Class<? extends DomainEvent>> eventsClasses,
                                Map<String, DomainEventSubscriber<DomainEvent>> subscribers) {
        this.subscribersOfEvents = subscribersOfEvents;
        this.eventsClasses = eventsClasses;
        this.subscribers = subscribers;
    }
}
