package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.domain.exceptions.HeroNameInvalidLength;
import com.example.heroes.heroes.domain.exceptions.HeroNameNull;
import com.example.heroes.shared.StringValueObject;

public final class HeroName extends StringValueObject {

    public HeroName(String value) {
        super(value);
        checkIsNotNull(value);
        checkLengthIsValid(value);
    }

    private void checkIsNotNull(String value) {
        if (value == null) throw new HeroNameNull();
    }

    private void checkLengthIsValid(String value) {
        if (value.isBlank() || value.length() > 20) throw new HeroNameInvalidLength();
    }
}
