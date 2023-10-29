package com.example.heroes.versus.application;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusRepository;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainRepository;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class VersusModuleTest extends UnitTestModule {

    protected VersusRepository repository;
    protected HeroRepository heroRepository;
    protected VillainRepository villainRepository;

    @Override
    protected void setUp() {
        super.setUp();
        repository = mock(VersusRepository.class);
        heroRepository = mock(HeroRepository.class);
        villainRepository = mock(VillainRepository.class);
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

    public void shouldSearch(List<Versus> versus) {
        when(repository.search(any(Criteria.class))).thenReturn(versus);
    }

    public void shouldSearch(List<Versus> versus1, List<Versus> versus2) {
        when(repository.search(any(Criteria.class))).thenReturn(versus1).thenReturn(versus2);
    }

    public void shouldSearchHero(Hero hero) {
        when(heroRepository.search(hero.getId())).thenReturn(Optional.of(hero));
    }

    public void shouldSearchVillain(Villain villain) {
        when(villainRepository.search(villain.getId())).thenReturn(Optional.of(villain));
    }
}
