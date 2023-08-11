package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.domain.exceptions.HeroNameInvalidLengthException;
import com.example.heroes.heroes.domain.exceptions.HeroNameNullException;
import com.example.heroes.shared.domain.StringValueObject;

public final class HeroName extends StringValueObject {

    public HeroName(String value) {
        super(value);
        checkIsNotNull(value);
        checkLengthIsValid(value);
    }

    private void checkIsNotNull(String value) {
        if (value == null) throw new HeroNameNullException();
    }

    private void checkLengthIsValid(String value) {
        if (value.isBlank() || value.length() > 20) throw new HeroNameInvalidLengthException();
    }
}
