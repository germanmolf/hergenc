package germanmolf.hergenc.versus.application.find;

import germanmolf.hergenc.versus.application.VersusModuleTest;
import germanmolf.hergenc.versus.domain.Versus;
import germanmolf.hergenc.versus.domain.VersusIdMother;
import germanmolf.hergenc.versus.domain.VersusMother;
import germanmolf.hergenc.versus.domain.exceptions.VersusNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

final class VersusFinderTest extends VersusModuleTest {

    private VersusFinder finder;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        finder = new VersusFinder(repository);
    }

    @Test
    void find_an_existing_versus() {
        Versus versus = VersusMother.random();
        VersusResponse response = VersusResponseMother.fromAggregate(versus);
        String id = versus.id().value();
        shouldSearch(versus);

        assertEquals(response, finder.find(id));
    }

    @Test
    void throw_an_exception_when_versus_not_found() {
        String id = VersusIdMother.randomValue();

        assertThrows(VersusNotFoundException.class, () -> finder.find(id));
    }
}
