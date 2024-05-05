package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.heroes.domain.exceptions.HeroPowerInvalidLengthException;
import germanmolf.hergenc.heroes.domain.exceptions.HeroPowerNullException;
import germanmolf.hergenc.shared.domain.StringValueObject;

public final class HeroPower extends StringValueObject {

    public HeroPower(String value) {
        super(value);
        checkIsNotNull(value);
        checkIsValid(value);
    }

    private void checkIsNotNull(String value) {
        if (value == null) throw new HeroPowerNullException();
    }

    private void checkIsValid(String value) {
        if (value.isBlank() || value.length() > 30) throw new HeroPowerInvalidLengthException();
    }
}
