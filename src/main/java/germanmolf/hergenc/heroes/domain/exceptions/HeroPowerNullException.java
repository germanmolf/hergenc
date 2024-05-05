package germanmolf.hergenc.heroes.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class HeroPowerNullException extends DomainError {

    public HeroPowerNullException() {
        super("hero_power_null", "The hero power is null");
    }
}
