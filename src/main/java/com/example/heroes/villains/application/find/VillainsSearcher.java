package com.example.heroes.villains.application.find;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.villains.domain.VillainRepository;

import java.util.List;
import java.util.stream.Collectors;

public final class VillainsSearcher {

    private final VillainRepository repository;

    public VillainsSearcher(VillainRepository repository) {
        this.repository = repository;
    }

    public List<VillainResponse> search(Criteria criteria) {
        return repository.search(criteria)
                .stream().map(VillainResponse::fromAggregate)
                .collect(Collectors.toList());
    }
}
