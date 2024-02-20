package com.example.heroes.shared.infraestructure.event;

import com.example.heroes.shared.infraestructure.persistence.MySqlRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DomainEventRepositoryMySql extends MySqlRepository<DomainEventJpa> {

    public DomainEventRepositoryMySql(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, DomainEventJpa.class);
    }

    public void save(DomainEventJpa event) {
        persist(event);
    }

    public List<DomainEventJpa> searchAll() {
        return all();
    }

    public void delete(DomainEventJpa event) {
        remove(event);
    }
}
