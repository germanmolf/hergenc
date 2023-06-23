package com.example.heroes.heroes.domain;

import com.example.heroes.heroes.domain.exceptions.HeroPowerInvalidLength;
import com.example.heroes.heroes.domain.exceptions.HeroPowerNull;
import com.example.heroes.shared.StringValueObject;

public final class HeroPower extends StringValueObject {

    public HeroPower(String value) {
        super(value);
        checkIsNotNull(value);
        checkIsValid(value);
    }

    private void checkIsNotNull(String value) {
        if (value == null) throw new HeroPowerNull();
    }

    private void checkIsValid(String value) {
        if (value.isBlank() || value.length() > 30) throw new HeroPowerInvalidLength();
    }
}
