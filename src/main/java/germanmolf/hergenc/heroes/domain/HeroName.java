package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.heroes.domain.exceptions.HeroNameInvalidLengthException;
import germanmolf.hergenc.heroes.domain.exceptions.HeroNameNullException;
import germanmolf.hergenc.shared.domain.StringValueObject;

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
