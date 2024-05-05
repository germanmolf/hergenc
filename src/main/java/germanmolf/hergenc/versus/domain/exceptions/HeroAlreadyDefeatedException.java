package germanmolf.hergenc.versus.domain.exceptions;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.DomainError;

public final class HeroAlreadyDefeatedException extends DomainError {

    public HeroAlreadyDefeatedException(HeroId id) {
        super("hero_already_defeated", "The hero <%s> is already defeated".formatted(id.value()));
    }
}
