package com.example.heroes.versus.application.create;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusCriteria;
import com.example.heroes.versus.domain.VersusId;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.versus.domain.exceptions.HeroAlreadyDefeatedException;
import com.example.heroes.versus.domain.exceptions.VersusAlreadyExistsException;
import com.example.heroes.versus.domain.exceptions.VillainAlreadyDefeatedException;
import com.example.heroes.villains.application.find.VillainFinder;
import com.example.heroes.villains.domain.VillainId;

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
        checkVersusNotExists(versus.getId());
        checkHeroExists(versus.getHeroId().value());
        checkVillainExists(versus.getVillainId().value());
        checkHeroIsNotAlreadyDefeated(versus.getHeroId());
        checkVillainIsNotAlreadyDefeated(versus.getVillainId());
        repository.save(versus);
        eventBus.publish(versus.pullDomainEvents());
    }

    private void checkVersusNotExists(VersusId id) {
        repository.search(id).ifPresent(versus -> {
            throw new VersusAlreadyExistsException(versus.getId());
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
