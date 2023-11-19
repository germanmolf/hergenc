package com.example.heroes.versus.infraestructure.persistence;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.MySqlRepository;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusId;
import com.example.heroes.versus.domain.VersusRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VersusRepositoryMySql extends MySqlRepository<VersusJpa> implements VersusRepository {

    public VersusRepositoryMySql(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, VersusJpa.class);
    }

    @Override
    public void save(Versus versus) {
        persist(VersusJpa.fromAggregate(versus));
    }

    @Override
    public Optional<Versus> search(VersusId id) {
        return byId(id).map(VersusJpa::fromJpaEntity);
    }

    @Override
    public List<Versus> search(Criteria criteria) {
        return byCriteria(criteria).stream().map(VersusJpa::fromJpaEntity).toList();
    }

    @Override
    public Long count(Criteria criteria) {
        return countByCriteria(criteria);
    }
}
