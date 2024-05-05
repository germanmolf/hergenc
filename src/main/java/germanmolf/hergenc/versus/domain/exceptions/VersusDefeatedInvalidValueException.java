package germanmolf.hergenc.versus.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class VersusDefeatedInvalidValueException extends DomainError {

    public VersusDefeatedInvalidValueException(String value) {
        super("versus_defeated_invalid_value", "The versus defeated value <%s> is not valid".formatted(value));
    }
}
