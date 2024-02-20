package com.example.heroes.shared.infraestructure.event;

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

    public List<EventQueued> searchAll() {
        return all();
    }

    public void delete(EventQueued event) {
        remove(event);
    }
}
