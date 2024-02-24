package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.MySqlRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomainEventRepositoryMySql extends MySqlRepository<EventQueued> {

    public DomainEventRepositoryMySql(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, EventQueued.class);
    }

    public void save(EventQueued event) {
        persist(event);
    }

    public List<EventQueued> search(Criteria criteria) {
        return byCriteria(criteria);
    }

    public void delete(EventQueued event) {
        remove(event);
    }
}
