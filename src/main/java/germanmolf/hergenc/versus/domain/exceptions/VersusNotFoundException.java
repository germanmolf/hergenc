package germanmolf.hergenc.versus.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.versus.domain.VersusId;

public final class VersusNotFoundException extends DomainError {

    public VersusNotFoundException(VersusId id) {
        super("versus_not_found", "Not found versus <%s>".formatted(id.value()));
    }
}
