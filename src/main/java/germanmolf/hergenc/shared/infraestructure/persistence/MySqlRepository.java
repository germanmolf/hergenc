package germanmolf.hergenc.shared.infraestructure.persistence;

import germanmolf.hergenc.shared.domain.Identifier;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.event.EventQueued;
import jakarta.persistence.criteria.CriteriaQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public abstract class MySqlRepository<T> {

    private final SessionFactory sessionFactory;
    private final Class<T> aggregateClass;
    private final CriteriaConverter<T> criteriaConverter;

    public MySqlRepository(SessionFactory sessionFactory, Class<T> aggregateClass) {
        this.sessionFactory = sessionFactory;
        this.aggregateClass = aggregateClass;
        this.criteriaConverter = new CriteriaConverter<>(sessionFactory.getCriteriaBuilder());
    }

    protected void persist(T entity) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.merge(entity);
            session.getTransaction().commit();
        }
    }

    protected Optional<T> byId(Identifier id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.find(aggregateClass, id.value()));
        }
    }

    protected List<T> byCriteria(Criteria criteria) {
        CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(criteria, aggregateClass);
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            List<T> list = session.createQuery(hibernateCriteria)
                    .setFirstResult(criteria.getStart())
                    .setMaxResults(criteria.getLimit())
                    .getResultList();
            session.getTransaction().commit();
            return list;
        }
    }

    protected Long countByCriteria(Criteria criteria) {
        CriteriaQuery<Long> hibernateCriteria = criteriaConverter.convertForCount(criteria, aggregateClass);
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery(hibernateCriteria).getSingleResult();
        }
    }

    public void remove(EventQueued event) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            EventQueued entityDetached = session.contains(event) ? event : session.find(EventQueued.class, event.getId());
            session.remove(entityDetached);
            session.getTransaction().commit();
        }
    }

}
