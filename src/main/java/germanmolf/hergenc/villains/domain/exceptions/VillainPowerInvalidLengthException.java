package germanmolf.hergenc.villains.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class VillainPowerInvalidLengthException extends DomainError {

    public VillainPowerInvalidLengthException() {
        super("villain_power_invalid_length", "The villain power length must be between 1 and 30 letters");
    }
}
