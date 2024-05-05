package germanmolf.hergenc.versus.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.villains.domain.VillainId;

public final class VillainAlreadyDefeatedException extends DomainError {

    public VillainAlreadyDefeatedException(VillainId id) {
        super("villain_already_defeated", "The villain <%s> is already defeated".formatted(id.value()));
    }
}
