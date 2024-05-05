package germanmolf.hergenc.heroes.application.update;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroRepository;
import germanmolf.hergenc.heroes.domain.exceptions.HeroNotFoundException;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.event.DomainEventSubscriber;
import germanmolf.hergenc.versus.domain.VersusCreatedEvent;
import germanmolf.hergenc.versus.domain.VersusDefeated;
import germanmolf.hergenc.villains.domain.VillainId;

@Injectable
public final class UpdateHeroOnVersusCreated implements DomainEventSubscriber<VersusCreatedEvent> {

    private final HeroRepository repository;

    public UpdateHeroOnVersusCreated(HeroRepository repository) {
        this.repository = repository;
    }

    @Override
    public void on(VersusCreatedEvent event) {
        HeroId heroId = new HeroId(event.heroId());
        Hero hero = repository.search(heroId).orElseThrow(() -> new HeroNotFoundException(heroId));
        VersusDefeated versusDefeated = VersusDefeated.fromValue(event.defeated());
        VillainId villainId = new VillainId(event.villainId());

        if (versusDefeated.villainIsDefeated() && !hero.hasDefeated(villainId)) {
            hero.addVillainDefeated(villainId);
        }

        if (versusDefeated.heroIsDefeated() && !hero.isDefeated()) {
            hero.defeatedBy(villainId);
        }
        repository.save(hero);
    }

    @Override
    public String subscriberName() {
        return "update.hero";
    }

}
