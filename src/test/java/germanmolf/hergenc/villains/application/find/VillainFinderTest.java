package germanmolf.hergenc.villains.application.find;

import germanmolf.hergenc.villains.VillainModuleTest;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import germanmolf.hergenc.villains.domain.VillainMother;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class VillainFinderTest extends VillainModuleTest {

    private VillainFinder finder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        finder = new VillainFinder(repository);
    }

    @Test
    void find_an_existing_villain() {
        Villain villain = VillainMother.random();
        VillainResponse response = VillainResponseMother.fromAggregate(villain);
        String id = villain.id().value();
        shouldSearch(villain);

        assertEquals(response, finder.find(id));
    }

    @Test
    void throw_an_exception_when_villain_not_found() {
        String id = VillainIdMother.randomValue();

        assertThrows(VillainNotFoundException.class, () -> finder.find(id));
    }
}
