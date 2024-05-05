package germanmolf.hergenc.villains.application.update;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.villains.VillainModuleTest;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import germanmolf.hergenc.villains.domain.VillainMother;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;
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
