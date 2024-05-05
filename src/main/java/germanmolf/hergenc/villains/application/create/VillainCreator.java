package germanmolf.hergenc.villains.application.create;

import germanmolf.hergenc.shared.domain.Injectable;
import germanmolf.hergenc.shared.domain.event.EventBus;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.VillainRepository;
import germanmolf.hergenc.villains.domain.exceptions.VillainAlreadyExistsException;

@Injectable
public final class VillainCreator {

    private final VillainRepository repository;
    private final EventBus eventBus;

    public VillainCreator(VillainRepository repository, EventBus eventBus) {
        this.repository = repository;
        this.eventBus = eventBus;
    }

    public void create(CreateVillainRequest request) {
        Villain villain = Villain.create(request.id(), request.name(), request.power());
        checkVillainNotExists(villain.id());
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
