package com.example.heroes.heroes.application;

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

    public void create(String id, String name, String power) {
        Hero hero = Hero.create(id, name, power);
        repository.save(hero);
        eventBus.publish(hero.pullDomainEvents());
    }
}
