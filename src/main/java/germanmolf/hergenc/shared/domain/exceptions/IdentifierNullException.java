package germanmolf.hergenc.shared.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class IdentifierNullException extends DomainError {

    public IdentifierNullException(String aggregateRootName) {
        super(String.format("%s_identifier_null", aggregateRootName),
                String.format("The %s identifier is null", aggregateRootName));
    }
}
