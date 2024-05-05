package germanmolf.hergenc.versus.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;
import germanmolf.hergenc.versus.domain.VersusId;

public final class VersusAlreadyExistsException extends DomainError {

    public VersusAlreadyExistsException(VersusId id) {
        super("versus_already_exists", "The versus <%s> already exists".formatted(id.value()));
    }
}
