package com.example.heroes.versus.application.create.find;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.versus.domain.VersusRepository;

import java.util.List;

public final class VersusSearcher {

    private final VersusRepository repository;

    public VersusSearcher(VersusRepository repository) {
        this.repository = repository;
    }

    public List<VersusResponse> search(Criteria criteria) {
        return repository.search(criteria).stream().map(VersusResponse::fromAggregate).toList();
    }
}
