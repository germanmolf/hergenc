package germanmolf.hergenc.versus.application.find;

import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.versus.domain.VersusRepository;

import java.util.List;

@Injectable
public final class VersusSearcher {

    private final VersusRepository repository;

    public VersusSearcher(VersusRepository repository) {
        this.repository = repository;
    }

    public List<VersusResponse> search(Criteria criteria) {
        return repository.search(criteria).stream().map(VersusResponse::fromAggregate).toList();
    }
}
