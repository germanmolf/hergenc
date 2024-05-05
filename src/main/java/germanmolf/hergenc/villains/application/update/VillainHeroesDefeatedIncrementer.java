package germanmolf.hergenc.villains.application.update;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.VillainRepository;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;

@Injectable
public final class VillainHeroesDefeatedIncrementer {

    private final VillainRepository repository;

    public VillainHeroesDefeatedIncrementer(VillainRepository repository) {
        this.repository = repository;
    }

    public void increment(VillainId villainId, HeroId heroId) {
        Villain villain = repository.search(villainId).orElseThrow(() -> new VillainNotFoundException(villainId));
        if (!villain.hasDefeated(heroId)) {
            villain.addHeroDefeated(heroId);
            repository.save(villain);
        }
    }
}
