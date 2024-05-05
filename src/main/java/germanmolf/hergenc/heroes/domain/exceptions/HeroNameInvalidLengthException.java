package germanmolf.hergenc.heroes.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class HeroNameInvalidLengthException extends DomainError {

    public HeroNameInvalidLengthException() {
        super("hero_name_invalid_length", "The hero name length must be between 1 and 20 letters");
    }
}
