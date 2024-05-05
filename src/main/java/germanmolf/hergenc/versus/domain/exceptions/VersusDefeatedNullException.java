package germanmolf.hergenc.versus.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class VersusDefeatedNullException extends DomainError {

    public VersusDefeatedNullException() {
        super("versus_defeated_null", "The versus defeated is null");
    }
}
