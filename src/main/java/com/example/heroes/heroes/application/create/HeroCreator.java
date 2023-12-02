package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.heroes.domain.exceptions.HeroAlreadyExistsException;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.shared.domain.event.EventBus;

@Injectable
public final class HeroCreator {

    private final HeroRepository repository;
    private final EventBus eventBus;

    public HeroCreator(HeroRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CreateHeroRequest request) {
        Hero hero = Hero.create(request.id(), request.name(), request.power());
        checkHeroNotExists(hero.id());
        repository.save(hero);
        eventBus.publish(hero.pullDomainEvents());
    }

    private void checkHeroNotExists(HeroId id) {
        repository.search(id)
                .ifPresent(hero -> {
                    throw new HeroAlreadyExistsException(id);
                });
    }
}
