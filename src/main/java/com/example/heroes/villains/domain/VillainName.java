package com.example.heroes.villains.domain;

import com.example.heroes.shared.domain.StringValueObject;
import com.example.heroes.villains.domain.exceptions.VillainNameNullException;

public final class VillainName extends StringValueObject {

    public VillainName(String value) {
        super(value);
        checkNameIsNotNull(value);
    }

    private void checkNameIsNotNull(String value) {
        if (value == null) {
            throw new VillainNameNullException();
        }
    }
}
