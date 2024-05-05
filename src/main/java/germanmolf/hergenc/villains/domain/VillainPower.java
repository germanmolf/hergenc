package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.StringValueObject;
import germanmolf.hergenc.villains.domain.exceptions.VillainPowerInvalidLengthException;
import germanmolf.hergenc.villains.domain.exceptions.VillainPowerNullException;

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
