package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.Optional;

public interface HeroRepository {

    void save(Hero hero);

    Optional<Hero> search(HeroId id);

    List<Hero> search(Criteria criteria);

}
