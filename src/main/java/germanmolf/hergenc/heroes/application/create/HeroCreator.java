package germanmolf.hergenc.heroes.application.create;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroRepository;
import germanmolf.hergenc.heroes.domain.exceptions.HeroAlreadyExistsException;
import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.event.EventBus;

@Injectable
public final class HeroCreator {

    private final HeroRepository repository;
    private final EventBus eventBus;

    public HeroCreator(HeroRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CreateHeroRequest request) {
        Hero hero = Hero.create(request.id(), request.name(), request.power());
        checkHeroNotExists(hero.id());
        repository.save(hero);
        eventBus.publish(hero.pullDomainEvents());
    }

    private void checkHeroNotExists(HeroId id) {
        repository.search(id)
                .ifPresent(hero -> {
                    throw new HeroAlreadyExistsException(id);
                });
    }
}
