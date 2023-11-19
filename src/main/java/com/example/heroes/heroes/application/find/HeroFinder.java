package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.heroes.domain.exceptions.HeroNotFoundException;
import com.example.heroes.shared.domain.Injectable;

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
