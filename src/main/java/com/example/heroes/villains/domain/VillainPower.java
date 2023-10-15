package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.StringValueObject;
import com.example.heroes.villains.domain.exceptions.VillainPowerInvalidLengthException;
import com.example.heroes.villains.domain.exceptions.VillainPowerNullException;

public final class VillainPower extends StringValueObject {

    public VillainPower(String value) {
        super(value);
        checkIsNotNull(value);
        checkIsValid(value);
    }


    private void checkIsNotNull(String value) {
        if (value == null) throw new VillainPowerNullException();
    }

    private void checkIsValid(String value) {
        if (value.isBlank() || value.length() > 30) throw new VillainPowerInvalidLengthException();
    }
}
