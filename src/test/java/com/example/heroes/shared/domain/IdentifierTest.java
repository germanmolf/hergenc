package com.example.heroes.shared.domain;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.exceptions.IdentifierNotValidException;
import com.example.heroes.shared.domain.exceptions.IdentifierNullException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("UnitTest")

public final class IdentifierTest {

    @Test
    void throw_an_exception_when_id_is_null() {
        assertThrows(IdentifierNullException.class, () -> new HeroId(null));
    }

    @Test
    void throw_an_exception_when_id_is_not_valid() {
        assertThrows(IdentifierNotValidException.class, () -> new HeroId("qwe"));
    }
}
