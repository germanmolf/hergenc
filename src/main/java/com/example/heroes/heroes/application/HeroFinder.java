package com.example.heroes.heroes.application;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.heroes.domain.exceptions.HeroNotExist;

public final class HeroFinder {

    private final HeroRepository repository;

    public HeroFinder(HeroRepository repository) {
        this.repository = repository;
    }

    public HeroResponse find(String id) {
        HeroId heroId = new HeroId(id);
        return repository.search(heroId)
                .map(HeroResponse::fromAggregate)
                .orElseThrow(() -> new HeroNotExist(heroId));
    }
}
