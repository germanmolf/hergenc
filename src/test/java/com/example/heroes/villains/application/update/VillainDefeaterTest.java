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

public final class VillainDefeaterTest extends VillainModuleTest {

    private VillainDefeater defeater;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        defeater = new VillainDefeater(repository);
    }

    @Test
    void change_status_when_is_not_defeated() {
        Villain villain = VillainMother.withStatusActive();
        HeroId heroId = HeroIdMother.random();
        Villain villainUpdated = VillainMother.updatingStatusToDefeated(villain, heroId.value());
        shouldSearch(villain);

        defeater.defeat(villain.id(), heroId);

        shouldHaveSaved(villainUpdated);
    }

    @Test
    void no_change_status_when_is_already_defeated() {
        Villain villain = VillainMother.random();
        HeroId heroId = HeroIdMother.random();
        villain = VillainMother.updatingStatusToDefeated(villain, heroId.value());
        shouldSearch(villain);

        defeater.defeat(villain.id(), heroId);

        shouldNotHaveSaved(villain);
    }

    @Test
    void throw_an_exception_when_villain_not_found() {
        VillainId villainId = VillainIdMother.random();
        HeroId heroId = HeroIdMother.random();
        
        assertThrows(VillainNotFoundException.class, () -> defeater.defeat(villainId, heroId));
    }

}
