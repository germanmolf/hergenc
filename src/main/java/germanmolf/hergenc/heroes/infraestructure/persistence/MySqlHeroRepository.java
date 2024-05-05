package germanmolf.hergenc.heroes.infraestructure.persistence;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroRepository;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.persistence.MySqlRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlHeroRepository extends MySqlRepository<HeroJpa> implements HeroRepository {

    public MySqlHeroRepository(SessionFactory sessionFactory) {
        super(sessionFactory, HeroJpa.class);
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
