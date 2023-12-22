package com.example.heroes.heroes;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.application.UnitTestModule;
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
