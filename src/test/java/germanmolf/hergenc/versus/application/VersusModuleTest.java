package germanmolf.hergenc.versus.application;

import germanmolf.hergenc.heroes.application.find.HeroFinder;
import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.exceptions.HeroNotFoundException;
import germanmolf.hergenc.shared.application.UnitTestModule;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.versus.domain.Versus;
import germanmolf.hergenc.versus.domain.VersusRepository;
import germanmolf.hergenc.villains.application.find.VillainFinder;
import germanmolf.hergenc.villains.domain.VillainId;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class VersusModuleTest extends UnitTestModule {

    protected VersusRepository repository;
    protected HeroFinder heroFinder;
    protected VillainFinder villainFinder;

    @Override
    protected void setUp() {
        super.setUp();
        repository = mock(VersusRepository.class);
        heroFinder = mock(HeroFinder.class);
        villainFinder = mock(VillainFinder.class);
    }

    protected void shouldHaveSaved(Versus versus) {
        verify(repository, atLeastOnce()).save(versus);
    }

    protected void shouldNotHaveSaved(Versus versus) {
        verify(repository, never()).save(versus);
    }

    public void shouldSearch(Versus versus) {
        when(repository.search(versus.id())).thenReturn(Optional.of(versus));
    }

    public void shouldCount(Long heroCount, Long villainCount) {
        when(repository.count(any(Criteria.class))).thenReturn(heroCount, villainCount);
    }

    public void shouldNotFindHero(HeroId id) {
        when(heroFinder.find(id.value())).thenThrow(HeroNotFoundException.class);
    }

    public void shouldNotFindVillain(VillainId id) {
        when(villainFinder.find(id.value())).thenThrow(VillainNotFoundException.class);
    }
}
