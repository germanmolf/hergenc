package germanmolf.hergenc.villains.application.update;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.VillainRepository;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;

@Injectable
public final class VillainDefeater {

    private final VillainRepository repository;

    public VillainDefeater(VillainRepository repository) {
        this.repository = repository;
    }

    public void defeat(VillainId villainId, HeroId heroId) {
        Villain villain = repository.search(villainId).orElseThrow(() -> new VillainNotFoundException(villainId));
        if (!villain.isDefeated()) {
            villain.defeatedBy(heroId);
            repository.save(villain);
        }
    }
}
