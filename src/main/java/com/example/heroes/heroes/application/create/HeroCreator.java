package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.event.EventBus;

public final class HeroCreator {

    private final HeroRepository repository;
    private final EventBus eventBus;

    public HeroCreator(HeroRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CreateHeroRequest request) {
        Hero hero = Hero.create(request.getId(), request.getName(), request.getPower());
        repository.save(hero);
        eventBus.publish(hero.pullDomainEvents());
    }
}
