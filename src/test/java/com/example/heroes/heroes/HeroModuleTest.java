package com.example.heroes.heroes;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.application.ApplicationTestModule;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.*;

public abstract class HeroModuleTest extends ApplicationTestModule {

    protected HeroRepository repository;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        repository = mock(HeroRepository.class);
    }

    protected void shouldHaveSaved(Hero hero) {
        verify(repository, atLeastOnce()).save(hero);
    }
}
