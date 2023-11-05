package com.example.heroes.versus.application;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.exceptions.HeroNotFoundException;
import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.villains.application.find.VillainFinder;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;

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
        when(repository.search(versus.getId())).thenReturn(Optional.of(versus));
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
