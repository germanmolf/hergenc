package germanmolf.hergenc.villains.domain;

import germanmolf.hergenc.shared.domain.StringValueObject;
import germanmolf.hergenc.villains.domain.exceptions.VillainNameInvalidLengthException;
import germanmolf.hergenc.villains.domain.exceptions.VillainNameNullException;

public final class VillainName extends StringValueObject {

    public VillainName(String value) {
        super(value);
        checkNameIsNotNull(value);
        checkLengthIsValid(value);
    }

    private void checkNameIsNotNull(String value) {
        if (value == null) {
            throw new VillainNameNullException();
        }
    }


    private void checkLengthIsValid(String value) {
        if (value.isBlank() || value.length() > 20) throw new VillainNameInvalidLengthException();
    }
}
