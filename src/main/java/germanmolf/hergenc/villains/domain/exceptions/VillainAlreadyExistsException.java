package germanmolf.hergenc.villains.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.villains.domain.VillainId;

public final class VillainAlreadyExistsException extends DomainError {

    public VillainAlreadyExistsException(VillainId id) {
        super("villain_already_exists", "The villain <%s> already exists".formatted(id.value()));
    }
}
