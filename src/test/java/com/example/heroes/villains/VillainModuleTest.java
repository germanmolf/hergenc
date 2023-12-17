package com.example.heroes.villains;

import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainRepository;
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
