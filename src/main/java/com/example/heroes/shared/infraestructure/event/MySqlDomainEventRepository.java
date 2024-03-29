package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.MySqlRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MySqlDomainEventRepository extends MySqlRepository<EventQueued> {

    public MySqlDomainEventRepository(SessionFactory sessionFactory) {
        super(sessionFactory, EventQueued.class);
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
