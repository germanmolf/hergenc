package com.example.heroes.villains.infraestructure.persistence;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.MySqlRepository;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlVillainRepository extends MySqlRepository<VillainJpa> implements VillainRepository {

    public MySqlVillainRepository(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, VillainJpa.class);
    }


    @Override
    public void save(Villain villain) {
        persist(VillainJpa.fromAggregate(villain));
    }

    @Override
    public Optional<Villain> search(VillainId id) {
        return byId(id).map(VillainJpa::fromJpaEntity);
    }

    @Override
    public List<Villain> searchAll() {
        return null;
    }

    @Override
    public List<Villain> search(Criteria criteria) {
        return null;
    }
}
