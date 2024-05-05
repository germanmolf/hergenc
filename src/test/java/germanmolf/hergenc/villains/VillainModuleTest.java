package germanmolf.hergenc.villains;

import germanmolf.hergenc.shared.application.UnitTestModule;
import germanmolf.hergenc.villains.domain.Villain;
import germanmolf.hergenc.villains.domain.VillainRepository;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class VillainModuleTest extends UnitTestModule {

    protected VillainRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(VillainRepository.class);
    }

    protected void shouldHaveSaved(Villain villain) {
        verify(repository, atLeastOnce()).save(villain);
    }

    protected void shouldNotHaveSaved(Villain villain) {
        verify(repository, never()).save(villain);
    }

    protected void shouldSearch(Villain villain) {
        when(repository.search(villain.id())).thenReturn(Optional.of(villain));
    }
}
