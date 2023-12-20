package com.example.heroes.villains.application.update;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainRepository;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;

@Injectable
public final class VillainHeroesDefeatedIncrementer {

    private final VillainRepository repository;

    public VillainHeroesDefeatedIncrementer(VillainRepository repository) {
        this.repository = repository;
    }

    public void increment(VillainId villainId, HeroId heroId) {
        Villain villain = repository.search(villainId).orElseThrow(() -> new VillainNotFoundException(villainId));
        if (!villain.hasDefeated(heroId)) {
            villain.addHeroDefeated(heroId);
            repository.save(villain);
        }
    }
}
