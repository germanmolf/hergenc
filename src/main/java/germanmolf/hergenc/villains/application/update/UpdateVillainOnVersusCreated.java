package germanmolf.hergenc.villains.application.update;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;
import germanmolf.hergenc.versus.domain.VersusCreatedEvent;
import germanmolf.hergenc.versus.domain.VersusDefeated;
import germanmolf.hergenc.villains.domain.VillainId;

@Injectable
public final class UpdateVillainOnVersusCreated implements DomainEventSubscriber<VersusCreatedEvent> {

    private final VillainHeroesDefeatedIncrementer incrementer;
    private final VillainDefeater defeater;

    public UpdateVillainOnVersusCreated(VillainHeroesDefeatedIncrementer incrementer, VillainDefeater defeater) {
        this.incrementer = incrementer;
        this.defeater = defeater;
    }

    @Override
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

    @Override
    public String subscriberName() {
        return "update.villain";
    }
}
