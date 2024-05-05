package germanmolf.hergenc.versus.application.create;

import germanmolf.hergenc.heroes.application.find.HeroFinder;
import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.domain.event.EventBus;
import germanmolf.hergenc.versus.domain.Versus;
import germanmolf.hergenc.versus.domain.VersusCriteria;
import germanmolf.hergenc.versus.domain.VersusId;
import germanmolf.hergenc.versus.domain.VersusRepository;
import germanmolf.hergenc.versus.domain.exceptions.HeroAlreadyDefeatedException;
import germanmolf.hergenc.versus.domain.exceptions.VersusAlreadyExistsException;
import germanmolf.hergenc.versus.domain.exceptions.VillainAlreadyDefeatedException;
import germanmolf.hergenc.villains.application.find.VillainFinder;
import germanmolf.hergenc.villains.domain.VillainId;

@Injectable
public final class VersusCreator {

    private final VersusRepository repository;
    private final EventBus eventBus;
    private final HeroFinder heroFinder;
    private final VillainFinder villainFinder;

    public VersusCreator(VersusRepository repository, EventBus eventBus, HeroFinder heroFinder, VillainFinder villainFinder) {
        this.repository = repository;
        this.eventBus = eventBus;
        this.heroFinder = heroFinder;
        this.villainFinder = villainFinder;
    }

    public void create(CreateVersusRequest request) {
        Versus versus = Versus.create(request.id(), request.heroId(), request.villainId(),
                request.defeated());
        checkVersusNotExists(versus.id());
        checkHeroExists(versus.heroId().value());
        checkVillainExists(versus.villainid().value());
        checkHeroIsNotAlreadyDefeated(versus.heroId());
        checkVillainIsNotAlreadyDefeated(versus.villainid());
        repository.save(versus);
        eventBus.publish(versus.pullDomainEvents());
    }

    private void checkVersusNotExists(VersusId id) {
        repository.search(id).ifPresent(versus -> {
            throw new VersusAlreadyExistsException(versus.id());
        });
    }

    private void checkHeroExists(String id) {
        heroFinder.find(id);
    }

    private void checkVillainExists(String id) {
        villainFinder.find(id);
    }

    private void checkHeroIsNotAlreadyDefeated(HeroId id) {
        Criteria criteria = VersusCriteria.heroDefeated(id);
        if (repository.count(criteria) > 0) {
            throw new HeroAlreadyDefeatedException(id);
        }
    }

    private void checkVillainIsNotAlreadyDefeated(VillainId id) {
        Criteria criteria = VersusCriteria.villainDefeated(id);
        if (repository.count(criteria) > 0) {
            throw new VillainAlreadyDefeatedException(id);
        }
    }
}
