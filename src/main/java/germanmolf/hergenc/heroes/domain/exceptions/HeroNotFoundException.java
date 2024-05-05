package germanmolf.hergenc.heroes.domain.exceptions;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.shared.domain.DomainError;

public final class HeroNotFoundException extends DomainError {

    public HeroNotFoundException(HeroId id) {
        super("hero_not_found", "Not found hero <%s>".formatted(id.value()));
    }
}
