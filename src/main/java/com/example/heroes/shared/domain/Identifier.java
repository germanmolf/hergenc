package com.example.heroes.shared.domain;

import com.example.heroes.shared.domain.exceptions.IdentifierNotValidException;
import com.example.heroes.shared.domain.exceptions.IdentifierNullException;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public abstract class Identifier implements Serializable {

    final protected String value;

    public Identifier(String value) {
        checkIsNotNull(value);
        checkValidUuid(value);
        this.value = value;
    }

    public String value() {
        return value;
    }

    private void checkIsNotNull(String value) {
        if (value == null) throw new IdentifierNullException();
    }

    private void checkValidUuid(String value) throws IllegalArgumentException {
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IdentifierNotValidException(value);
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
}
