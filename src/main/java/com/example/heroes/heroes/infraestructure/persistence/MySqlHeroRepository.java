package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.MySqlRepository;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlHeroRepository extends MySqlRepository<HeroJpa> implements HeroRepository {

    public MySqlHeroRepository(EntityManagerFactory entityManagerFactory) {
        super(entityManagerFactory, HeroJpa.class);
    }

    @Override
    public void save(Hero hero) {
        persist(HeroJpa.fromAggregate(hero));
    }

    @Override
    public Optional<Hero> search(HeroId id) {
        return byId(id).map(HeroJpa::toAggregate);
    }

    @Override
    public List<Hero> search(Criteria criteria) {
        return byCriteria(criteria).stream().map(HeroJpa::toAggregate).toList();
    }
}
