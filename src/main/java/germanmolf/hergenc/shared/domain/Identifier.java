package germanmolf.hergenc.shared.domain;

import germanmolf.hergenc.shared.domain.exceptions.IdentifierNotValidException;
import germanmolf.hergenc.shared.domain.exceptions.IdentifierNullException;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identifier implements Serializable {

    private final String value;

    public Identifier(String value, String aggregateRootName) {
        checkIsNotNull(value, aggregateRootName);
        checkValidUuid(value, aggregateRootName);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private void checkIsNotNull(String value, String aggregateRootName) {
        if (value == null) throw new IdentifierNullException(aggregateRootName);
    }

    private void checkValidUuid(String value, String aggregateRootName) throws IllegalArgumentException {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IdentifierNotValidException(value, aggregateRootName);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Identifier that = (Identifier) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
