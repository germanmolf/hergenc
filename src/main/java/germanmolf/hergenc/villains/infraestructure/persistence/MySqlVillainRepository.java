package germanmolf.hergenc.villains.infraestructure.persistence;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.persistence.MySqlRepository;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.VillainRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlVillainRepository extends MySqlRepository<VillainJpa> implements VillainRepository {

    public MySqlVillainRepository(SessionFactory sessionFactory) {
        super(sessionFactory, VillainJpa.class);
    }

    @Override
    public void save(Villain villain) {
        persist(VillainJpa.fromAggregate(villain));
    }

    @Override
    public Optional<Villain> search(VillainId id) {
        return byId(id).map(VillainJpa::toAggregate);
    }

    @Override
    public List<Villain> search(Criteria criteria) {
        return byCriteria(criteria).stream().map(VillainJpa::toAggregate).toList();
    }
}
