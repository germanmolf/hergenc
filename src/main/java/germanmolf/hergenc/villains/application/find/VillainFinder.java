package germanmolf.hergenc.villains.application.find;

import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.VillainRepository;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;

@Injectable
public final class VillainFinder {

    private final VillainRepository repository;

    public VillainFinder(VillainRepository repository) {
        this.repository = repository;
    }

    public VillainResponse find(String id) {
        VillainId villainId = new VillainId(id);
        return this.repository.search(villainId)
                .map(VillainResponse::fromAggregate)
                .orElseThrow(() -> new VillainNotFoundException(villainId));
    }
}
