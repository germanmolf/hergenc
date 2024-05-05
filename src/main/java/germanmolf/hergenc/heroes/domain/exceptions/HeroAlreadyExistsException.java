package germanmolf.hergenc.heroes.domain.exceptions;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.DomainError;

public final class HeroAlreadyExistsException extends DomainError {

    public HeroAlreadyExistsException(HeroId id) {
        super("hero_already_exists", "The hero <%s> already exists".formatted(id.value()));
    }
}
