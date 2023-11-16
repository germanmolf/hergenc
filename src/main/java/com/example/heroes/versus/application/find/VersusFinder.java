package com.example.heroes.versus.application.find;

import com.example.heroes.versus.domain.VersusId;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.versus.domain.exceptions.VersusNotFoundException;

public final class VersusFinder {

    private final VersusRepository repository;

    public VersusFinder(VersusRepository repository) {
        this.repository = repository;
    }

    public VersusResponse find(String id) {
        VersusId versusId = new VersusId(id);
        return repository.search(versusId)
                .map(VersusResponse::fromAggregate)
                .orElseThrow(() -> new VersusNotFoundException(versusId));
    }
}
