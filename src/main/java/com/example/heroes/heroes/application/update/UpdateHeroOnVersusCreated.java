package com.example.heroes.heroes.application.update;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.heroes.domain.exceptions.HeroNotFoundException;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusDefeated;
import com.example.heroes.villains.domain.VillainId;

@Injectable
public final class UpdateHeroOnVersusCreated {

    private final HeroRepository repository;

    public UpdateHeroOnVersusCreated(HeroRepository repository) {
        this.repository = repository;
    }

    public void on(VersusCreatedEvent event) {
        HeroId heroId = new HeroId(event.getHeroId());
        Hero hero = repository.search(heroId).orElseThrow(() -> new HeroNotFoundException(heroId));
        VersusDefeated versusDefeated = VersusDefeated.fromValue(event.getDefeated());
        VillainId villainId = new VillainId(event.getVillainId());

        if (versusDefeated.villainIsDefeated() && !hero.hasDefeated(villainId)) {
            hero.addVillainDefeated(villainId);
        }
        if (versusDefeated.heroIsDefeated()) {
            hero.defeatedBy(villainId);
        }
        repository.save(hero);
    }
}
