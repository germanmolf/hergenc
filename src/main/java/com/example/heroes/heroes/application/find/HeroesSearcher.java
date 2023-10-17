package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.criteria.Criteria;

import java.util.List;

public final class HeroesSearcher {

    private final HeroRepository repository;

    public HeroesSearcher(HeroRepository repository) {
        this.repository = repository;
    }

    public List<HeroResponse> search(Criteria criteria) {
        return repository.search(criteria)
                .stream().map(HeroResponse::fromAggregate)
                .toList();
    }
}
