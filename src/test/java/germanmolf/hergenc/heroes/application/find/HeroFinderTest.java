package germanmolf.hergenc.heroes.application.find;

import germanmolf.hergenc.heroes.HeroModuleTest;
import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.heroes.domain.HeroMother;
import germanmolf.hergenc.heroes.domain.exceptions.HeroNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class HeroFinderTest extends HeroModuleTest {

    private HeroFinder finder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        finder = new HeroFinder(repository);
    }

    @Test
    void find_an_existing_hero() {
        Hero hero = HeroMother.random();
        HeroResponse response = HeroResponseMother.fromAggregate(hero);
        String id = hero.id().value();
        shouldSearch(hero);

        assertEquals(response, finder.find(id));
    }

    @Test
    void throw_an_exception_when_hero_not_found() {
        String id = HeroIdMother.randomValue();

        assertThrows(HeroNotFoundException.class, () -> finder.find(id));
    }
}
