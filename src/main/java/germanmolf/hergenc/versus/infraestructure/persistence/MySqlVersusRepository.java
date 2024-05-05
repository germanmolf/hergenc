package germanmolf.hergenc.versus.infraestructure.persistence;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.persistence.MySqlRepository;
import germanmolf.hergenc.versus.domain.Versus;
import germanmolf.hergenc.versus.domain.VersusId;
import germanmolf.hergenc.versus.domain.VersusRepository;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MySqlVersusRepository extends MySqlRepository<VersusJpa> implements VersusRepository {

    public MySqlVersusRepository(SessionFactory sessionFactory) {
        super(sessionFactory, VersusJpa.class);
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
