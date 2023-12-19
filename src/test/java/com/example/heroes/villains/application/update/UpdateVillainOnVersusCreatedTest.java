package com.example.heroes.villains.application.update;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusCreatedEventMother;
import com.example.heroes.villains.VillainModuleTest;
import com.example.heroes.villains.domain.VillainId;
import com.example.heroes.villains.domain.VillainIdMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

public final class UpdateVillainOnVersusCreatedTest extends VillainModuleTest {

    private UpdateVillainOnVersusCreated subscriber;
    private VillainHeroesDefeatedIncrementer incrementer;
    private VillainDefeater defeater;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        incrementer = mock(VillainHeroesDefeatedIncrementer.class);
        defeater = mock(VillainDefeater.class);
        subscriber = new UpdateVillainOnVersusCreated(incrementer, defeater);
    }

    @Test
    void call_incrementer_when_hero_is_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withHeroDefeated();
        VillainId villainId = VillainIdMother.create(event.getVillainId());
        HeroId heroId = HeroIdMother.create(event.getHeroId());

        subscriber.on(event);

        shouldCallIncrementer(villainId, heroId);
    }

    @Test
    void call_defeater_when_villain_is_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withVillainDefeated();
        VillainId villainId = VillainIdMother.create(event.getVillainId());
        HeroId heroId = HeroIdMother.create(event.getHeroId());

        subscriber.on(event);

        shouldCallDefeater(villainId, heroId);
    }

    private void shouldCallIncrementer(VillainId villainId, HeroId heroId) {
        verify(incrementer, atLeastOnce()).increment(villainId, heroId);
    }

    private void shouldCallDefeater(VillainId villainId, HeroId heroId) {
        verify(defeater, atLeastOnce()).defeat(villainId, heroId);
    }
}
