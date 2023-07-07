package com.example.heroes.heroes.application;

import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.criteria.Criteria;

import java.util.List;
import java.util.stream.Collectors;

public final class HeroesSearcher {

    private final HeroRepository repository;

    public HeroesSearcher(HeroRepository repository) {
        this.repository = repository;
    }

    public List<HeroResponse> search(Criteria criteria) {
        return repository.search(criteria)
                .stream().map(HeroResponse::fromAggregate)
                .collect(Collectors.toList());
    }
}
