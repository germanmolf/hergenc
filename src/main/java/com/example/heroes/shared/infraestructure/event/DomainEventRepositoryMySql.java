package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.Utils;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomainEventRepositoryMySql {

    private final SessionFactory sessionFactory;

    public DomainEventRepositoryMySql(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void save(EventQueued event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            NativeQuery<EventQueued> query = session.createNativeQuery(
                    "INSERT INTO event_queued (id,  aggregate_id, occurred_on, name, subscribers, body) " +
                            "VALUES (:id, :aggregateId, :occurredOn, :name, :subscribers, :body);", EventQueued.class);
            query.setParameter("id", event.getId())
                    .setParameter("aggregateId", event.getAggregateId())
                    .setParameter("occurredOn", event.getOccurredOn())
                    .setParameter("name", event.getName())
                    .setParameter("subscribers", Utils.jsonEncode(event.getSubscribers()))
                    .setParameter("body", Utils.jsonEncode(event.getBody()));
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public void update(EventQueued event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            NativeQuery<EventQueued> query = session.createNativeQuery(
                    "UPDATE event_queued set subscribers = :subscribers WHERE id = :id", EventQueued.class);
            query.setParameter("id", event.getId())
                    .setParameter("subscribers", Utils.jsonEncode(event.getSubscribers()));
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }

    public List<EventQueued> findAll() {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<EventQueued> query = session.createNativeQuery(
                    "SELECT * FROM event_queued ORDER BY occurred_on ASC", EventQueued.class);
            List<EventQueued> list = query.list();
            session.getTransaction().commit();
            return list;
        }
    }

    public void delete(EventQueued event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            Query<EventQueued> query = session.createNativeQuery(
                    "DELETE FROM event_queued WHERE id = :id", EventQueued.class);
            query.setParameter("id", event.getId());
            query.executeUpdate();
            session.getTransaction().commit();
        }
    }
}
