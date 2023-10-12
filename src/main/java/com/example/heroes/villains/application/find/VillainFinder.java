package com.example.heroes.villains.application.find;

import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainRepository;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;

public final class VillainFinder {

    private final VillainRepository repository;

    public VillainFinder(VillainRepository repository) {
        this.repository = repository;
    }

    public VillainResponse find(String id) {
        VillainId villainId = new VillainId(id);
        return this.repository.search(villainId)
                .map(VillainResponse::fromAggregate)
                .orElseThrow(() -> new VillainNotFoundException(villainId));
    }
}
