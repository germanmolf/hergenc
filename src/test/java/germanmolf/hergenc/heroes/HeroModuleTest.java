package germanmolf.hergenc.heroes;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroRepository;
import germanmolf.hergenc.shared.application.UnitTestModule;
import org.junit.jupiter.api.BeforeEach;

import java.util.Optional;

import static org.mockito.Mockito.*;

public abstract class HeroModuleTest extends UnitTestModule {

    protected HeroRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(HeroRepository.class);
    }

    protected void shouldHaveSaved(Hero hero) {
        verify(repository, atLeastOnce()).save(hero);
    }

    protected void shouldNotHaveSaved(Hero hero) {
        verify(repository, never()).save(hero);
    }

    protected void shouldSearch(Hero hero) {
        when(repository.search(hero.id())).thenReturn(Optional.of(hero));
    }
}
