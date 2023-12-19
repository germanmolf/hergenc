package com.example.heroes.villains.application.update;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainRepository;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;

@Injectable
public final class VillainDefeater {

    private final VillainRepository repository;

    public VillainDefeater(VillainRepository repository) {
        this.repository = repository;
    }

    public void defeat(VillainId villainId, HeroId heroId) {
        Villain villain = repository.search(villainId).orElseThrow(() -> new VillainNotFoundException(villainId));
        if (!villain.isDefeated()) {
            villain.defeatedBy(heroId);
            repository.save(villain);
        }
    }
}
