package germanmolf.hergenc.versus.application.find;

import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.versus.domain.VersusId;
import germanmolf.hergenc.versus.domain.VersusRepository;
import germanmolf.hergenc.versus.domain.exceptions.VersusNotFoundException;

@Injectable
public final class VersusFinder {

    private final VersusRepository repository;

    public VersusFinder(VersusRepository repository) {
        this.repository = repository;
    }

    public VersusResponse find(String id) {
        VersusId versusId = new VersusId(id);
        return repository.search(versusId)
                .map(VersusResponse::fromAggregate)
                .orElseThrow(() -> new VersusNotFoundException(versusId));
    }
}
