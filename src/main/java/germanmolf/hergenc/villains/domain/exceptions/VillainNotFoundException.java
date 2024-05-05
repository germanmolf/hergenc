package germanmolf.hergenc.villains.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.villains.domain.VillainId;

public final class VillainNotFoundException extends DomainError {

    public VillainNotFoundException(VillainId id) {
        super("villain_not_found", "Not found villain <%s>".formatted(id.value()));
    }
}
