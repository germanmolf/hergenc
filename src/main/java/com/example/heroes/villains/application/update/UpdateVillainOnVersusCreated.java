package com.example.heroes.villains.application.update;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.Injectable;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusDefeated;
import com.example.heroes.villains.domain.VillainId;

@Injectable
public final class UpdateVillainOnVersusCreated {

    private final VillainHeroesDefeatedIncrementer incrementer;
    private final VillainDefeater defeater;

    public UpdateVillainOnVersusCreated(VillainHeroesDefeatedIncrementer incrementer, VillainDefeater defeater) {
        this.incrementer = incrementer;
        this.defeater = defeater;
    }

    public void on(VersusCreatedEvent event) {
        VillainId villainId = new VillainId(event.villainId());
        HeroId heroId = new HeroId(event.heroId());
        VersusDefeated versusDefeated = VersusDefeated.fromValue(event.defeated());
        if (versusDefeated.heroIsDefeated()) {
            incrementer.increment(villainId, heroId);
        }
        if (versusDefeated.villainIsDefeated()) {
            defeater.defeat(villainId, heroId);
        }
    }
}
