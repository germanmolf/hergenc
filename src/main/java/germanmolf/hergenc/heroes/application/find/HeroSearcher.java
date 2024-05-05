package germanmolf.hergenc.heroes.application.find;

import germanmolf.hergenc.heroes.domain.HeroRepository;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.criteria.Criteria;

import java.util.List;

@Injectable
public final class HeroSearcher {

    private final HeroRepository repository;

    public HeroSearcher(HeroRepository repository) {
        this.repository = repository;
    }

    public List<HeroResponse> search(Criteria criteria) {
        return repository.search(criteria)
                .stream().map(HeroResponse::fromAggregate)
                .toList();
    }
}
