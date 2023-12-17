package com.example.heroes.villains.application.update;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.villains.VillainModuleTest;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainIdMother;
import com.example.heroes.villains.domain.VillainMother;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class VillainHeroesDefeatedIncrementerTest extends VillainModuleTest {

    private VillainHeroesDefeatedIncrementer incrementer;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        incrementer = new VillainHeroesDefeatedIncrementer(repository);
    }

    @Test
    void increment_total_and_include_hero_defeated_when_hero_is_defeated() {
        Villain villain = VillainMother.random();
        HeroId heroId = HeroIdMother.random();
        Villain villainUpdated = VillainMother.updatingHeroesDefeated(villain, heroId);
        shouldSearch(villain);

        incrementer.increment(villain.id(), heroId);

        shouldHaveSaved(villainUpdated);
    }

    @Test
    void not_increment_and_not_include_hero_when_hero_is_already_defeated() {
        Villain villain = VillainMother.random();
        HeroId heroId = HeroIdMother.random();
        villain = VillainMother.updatingHeroesDefeated(villain, heroId);
        shouldSearch(villain);

        incrementer.increment(villain.id(), heroId);

        shouldNotHaveSaved(villain);
    }

    @Test
    void throw_an_exception_when_villain_not_found() {
        VillainId villainId = VillainIdMother.random();
        HeroId heroId = HeroIdMother.random();

        assertThrows(VillainNotFoundException.class, () -> incrementer.increment(villainId, heroId));
    }
}
