package germanmolf.hergenc.villains.application.find;

import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.villains.domain.VillainRepository;

import java.util.List;

@Injectable
public final class VillainSearcher {

    private final VillainRepository repository;

    public VillainSearcher(VillainRepository repository) {
        this.repository = repository;
    }

    public List<VillainResponse> search(Criteria criteria) {
        return repository.search(criteria)
                .stream().map(VillainResponse::fromAggregate)
                .toList();
    }
}
