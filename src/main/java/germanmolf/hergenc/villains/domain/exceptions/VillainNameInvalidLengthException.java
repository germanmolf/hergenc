package germanmolf.hergenc.villains.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class VillainNameInvalidLengthException extends DomainError {

    public VillainNameInvalidLengthException() {
        super("villain_name_invalid_length", "The villain name length must be between 1 and 20 letters");
    }
}
