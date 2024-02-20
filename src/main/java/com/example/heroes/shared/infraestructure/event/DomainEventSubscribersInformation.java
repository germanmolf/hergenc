package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.event.DomainEvent;
import com.example.heroes.shared.domain.event.DomainEventSubscriber;
import org.reflections.Reflections;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public final class DomainEventSubscribersInformation {

    private final ApplicationContext context;

    private final Map<String, HashSet<String>> information;
    private final Map<String, Class<? extends DomainEvent>> eventsClasses;
    private final Map<String, DomainEventSubscriber<DomainEvent>> subscribers;

    public DomainEventSubscribersInformation(ApplicationContext context) throws ReflectiveOperationException {
        this.context = context;
        information = new HashMap<>();
        eventsClasses = new HashMap<>();
        subscribers = new HashMap<>();
        scanDomainEventSubscribers();
    }

    public Set<String> getDomainEventSubscribersNames(String eventName) {
        return information.getOrDefault(eventName, new HashSet<>());
    }

    private void scanDomainEventSubscribers() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Reflections reflections = new Reflections("com.example.heroes");
        var subscribersClasses = reflections.getSubTypesOf(DomainEventSubscriber.class);

        for (var subscriberClass : subscribers) {
            DomainEventSubscriber<?> subscriber = context.getBean(subscriberClass);
        for (var subscriberClass : subscribersClasses) {
            DomainEventSubscriber<DomainEvent> subscriber = context.getBean(subscriberClass);
            String subscriberName = subscriber.subscriberName();

            Class<DomainEvent> eventClass = getSubscribedEventClass(subscriberClass);
            DomainEvent event = eventClass.getConstructor().newInstance();
            String eventName = event.eventName();

            HashSet<String> eventSubscribersNames = information.getOrDefault(eventName, new HashSet<>());
            eventSubscribersNames.add(subscriberName);
            information.put(eventName, eventSubscribersNames);

            eventsClasses.put(eventName, eventClass);

            subscribers.put(subscriberName, subscriber);
        }
    }

    private Class<DomainEvent> getSubscribedEventClass(Class<?> subscriberClass) {
        ParameterizedType genericInterface = (ParameterizedType) subscriberClass.getGenericInterfaces()[0];
        return (Class<DomainEvent>) genericInterface.getActualTypeArguments()[0];
    }

    public Class<? extends DomainEvent> getEventClass(String eventName) {
        return eventsClasses.get(eventName);
    }

    public DomainEventSubscriber<DomainEvent> getSubscriber(String subscriberName) {
        return subscribers.get(subscriberName);
    }

}
