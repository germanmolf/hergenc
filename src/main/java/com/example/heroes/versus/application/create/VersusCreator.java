package com.example.heroes.versus.application.create;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.versus.domain.exceptions.HeroAlreadyDefeatedException;
import com.example.heroes.versus.domain.exceptions.VersusAlreadyExistsException;
import com.example.heroes.versus.domain.exceptions.VillainAlreadyDefeatedException;
import com.example.heroes.villains.application.find.VillainFinder;

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

        if (repository.search(versus.getId()).isPresent()) {
            throw new VersusAlreadyExistsException(versus.getId());
        }

        heroFinder.find(request.heroId());
        villainFinder.find(request.villainId());

        Criteria criteriaHero = new Criteria().setFilter("defeated", "in", "hero,both").setFilter("heroId", "=",
                request.heroId());
        if (!repository.search(criteriaHero).isEmpty()) {
            throw new HeroAlreadyDefeatedException(versus.getHeroId());
        }

        Criteria criteriaVillain = new Criteria().setFilter("defeated", "in", "villain,both").setFilter("villainId",
                "=",
                request.villainId());
        if (!repository.search(criteriaVillain).isEmpty()) {
            throw new VillainAlreadyDefeatedException(versus.getVillainId());
        }

        repository.save(versus);
        eventBus.publish(versus.pullDomainEvents());
    }
}
