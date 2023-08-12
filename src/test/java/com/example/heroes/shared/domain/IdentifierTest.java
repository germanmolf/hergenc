package com.example.heroes.shared.domain;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroNameMother;
import com.example.heroes.heroes.domain.HeroPowerMother;
import com.example.heroes.shared.domain.exceptions.IdentifierNotValidException;
import com.example.heroes.shared.domain.exceptions.IdentifierNullException;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

@Tag("UnitTest")
public final class IdentifierTest {

    @Test
    void throw_an_exception_when_id_is_null() {
        String heroId = null;
        String heroName = HeroNameMother.randomValue();
        String heroPower = HeroPowerMother.randomValue();

        assertThrows(IdentifierNullException.class, () -> new Hero(heroId, heroName, heroPower));

    }

    @Test
    void throw_an_exception_when_id_is_not_valid() {
        String heroId = "qwe";
        String heroName = HeroNameMother.randomValue();
        String heroPower = HeroPowerMother.randomValue();

        assertThrows(IdentifierNotValidException.class, () -> new Hero(heroId, heroName, heroPower));
    }
}
