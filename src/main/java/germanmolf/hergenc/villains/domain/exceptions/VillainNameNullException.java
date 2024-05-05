package germanmolf.hergenc.villains.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class VillainNameNullException extends DomainError {

    public VillainNameNullException() {
        super("villain_name_null", "The villain name is null");
    }
}
