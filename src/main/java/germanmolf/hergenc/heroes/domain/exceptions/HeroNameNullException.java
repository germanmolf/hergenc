package germanmolf.hergenc.heroes.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class HeroNameNullException extends DomainError {

    public HeroNameNullException() {
        super("hero_name_null", "The hero name is null");
    }
}
