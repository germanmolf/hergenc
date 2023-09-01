package com.example.heroes.shared.infraestructure.persistence;

import com.example.heroes.shared.domain.Identifier;
import com.example.heroes.shared.domain.criteria.Criteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaQuery;

import java.util.List;
import java.util.Optional;

public abstract class MySqlRepository<T> {

    private final EntityManager entityManager;
    private final Class<T> aggregateClass;
    private final CriteriaConverter<T> criteriaConverter;

    public MySqlRepository(EntityManagerFactory entityManagerFactory, Class<T> aggregateClass) {
        this.aggregateClass = aggregateClass;
        this.entityManager = entityManagerFactory.createEntityManager();
        this.criteriaConverter = new CriteriaConverter<>(entityManagerFactory.getCriteriaBuilder());
    }

    protected void persist(T entity) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(entity);
            entityManager.flush();
            entityManager.clear();
            entityManager.getTransaction().commit();
        } finally {
            entityManager.getTransaction().rollback();
        }
    }

    protected Optional<T> byId(Identifier id) {
        return Optional.ofNullable(entityManager.find(aggregateClass, id.value()));
    }

    protected List<T> all() {
        CriteriaQuery<T> criteria = entityManager.getCriteriaBuilder().createQuery(aggregateClass);
        criteria.from(aggregateClass);
        return entityManager.createQuery(criteria).getResultList();
    }

    protected List<T> byCriteria(Criteria criteria) {
        CriteriaQuery<T> hibernateCriteria = criteriaConverter.convert(criteria, aggregateClass);
        return entityManager.createQuery(hibernateCriteria)
                .setFirstResult(criteria.getStart().orElse(0))
                .setMaxResults(criteria.getLimit().orElse(Integer.MAX_VALUE))
                .getResultList();
    }
}
