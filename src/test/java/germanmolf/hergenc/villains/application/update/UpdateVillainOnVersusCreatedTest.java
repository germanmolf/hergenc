package germanmolf.hergenc.villains.application.update;

import germanmolf.hergenc.heroes.domain.HeroId;
import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.versus.domain.VersusCreatedEvent;
import germanmolf.hergenc.versus.domain.VersusCreatedEventMother;
import germanmolf.hergenc.villains.VillainModuleTest;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import germanmolf.hergenc.villains.domain.VillainId;
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
        VillainId villainId = VillainIdMother.create(event.villainId());
        HeroId heroId = HeroIdMother.create(event.heroId());

        subscriber.on(event);

        shouldCallIncrementer(villainId, heroId);
    }

    @Test
    void call_defeater_when_villain_is_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withVillainDefeated();
        VillainId villainId = VillainIdMother.create(event.villainId());
        HeroId heroId = HeroIdMother.create(event.heroId());

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
