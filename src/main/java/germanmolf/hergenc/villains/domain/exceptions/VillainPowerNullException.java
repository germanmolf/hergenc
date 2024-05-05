package germanmolf.hergenc.villains.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class VillainPowerNullException extends DomainError {

    public VillainPowerNullException() {
        super("villain_power_null", "The villain power is null");
    }
}
