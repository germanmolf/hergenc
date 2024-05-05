package germanmolf.hergenc.shared.domain.exceptions;

import germanmolf.hergenc.shared.domain.DomainError;

public final class IdentifierNotValidException extends DomainError {

    public IdentifierNotValidException(String id, String aggregateRootName) {
        super(String.format("%s_identifier_not_valid", aggregateRootName),
                String.format("The %s identifier <%s> is not a valid UUID", aggregateRootName, id));
    }
}
