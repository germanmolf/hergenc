package germanmolf.hergenc.heroes.application.find;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroRepository;
import germanmolf.hergenc.heroes.domain.exceptions.HeroNotFoundException;
import germanmolf.hergenc.shared.domain.Injectable;

@Injectable
public final class HeroFinder {

    private final HeroRepository repository;

    public HeroFinder(HeroRepository repository) {
        this.repository = repository;
    }

    public HeroResponse find(String id) {
        HeroId heroId = new HeroId(id);
        return repository.search(heroId)
                .map(HeroResponse::fromAggregate)
                .orElseThrow(() -> new HeroNotFoundException(heroId));
    }
}
