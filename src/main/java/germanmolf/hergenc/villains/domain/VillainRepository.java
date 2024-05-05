package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface VillainRepository {


    void save(Villain villain);

    Optional<Villain> search(VillainId id);

    List<Villain> search(Criteria criteria);

}
