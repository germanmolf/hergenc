package com.example.heroes.villains.application.create;

import com.example.heroes.shared.domain.event.EventBus;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainRepository;
import com.example.heroes.villains.domain.exceptions.VillainAlreadyExistsException;

public final class VillainCreator {

    private final VillainRepository repository;
    private final EventBus eventBus;

    public VillainCreator(VillainRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CreateVillainRequest request) {
        Villain villain = Villain.create(request.getId(), request.getName(), request.getPower());
        checkVillainNotExists(villain.getId());
        repository.save(villain);
        eventBus.publish(villain.pullDomainEvents());
    }

    private void checkVillainNotExists(VillainId id) {
        repository.search(id)
                .ifPresent(villain -> {
                    throw new VillainAlreadyExistsException(id);
                });
    }
}
