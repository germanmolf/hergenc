package com.example.heroes.heroes.application.update;

import com.example.heroes.heroes.HeroModuleTest;
import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroMother;
import com.example.heroes.heroes.domain.exceptions.HeroNotFoundException;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusCreatedEventMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public final class UpdateHeroOnVersusCreatedTest extends HeroModuleTest {

    private UpdateHeroOnVersusCreated subscriber;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        subscriber = new UpdateHeroOnVersusCreated(repository);
    }

    @Test
    void increment_total_and_include_villain_defeated_when_villain_is_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withOnlyVillainDefeated();
        Hero hero = HeroMother.createWithId(event.heroId());
        Hero heroUpdated = HeroMother.updatingVillainsDefeated(hero, event.villainId());
        shouldSearch(hero);

        subscriber.on(event);

        shouldHaveSaved(heroUpdated);
    }

    @Test
    void not_increment_and_not_include_villain_when_villain_is_not_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withNoneDefeated();
        Hero hero = HeroMother.createWithId(event.heroId());
        Hero heroNotUpdated = HeroMother.copy(hero);
        shouldSearch(hero);

        subscriber.on(event);

        shouldHaveSaved(heroNotUpdated);
    }

    @Test
    void not_increment_and_not_include_villain_when_villain_is_already_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withOnlyVillainDefeated();
        Hero hero = HeroMother.createWithId(event.heroId());
        hero = HeroMother.updatingVillainsDefeated(hero, event.villainId());
        Hero heroNotUpdated = HeroMother.copy(hero);
        shouldSearch(hero);

        subscriber.on(event);

        shouldHaveSaved(heroNotUpdated);
    }

    @Test
    void change_status_when_is_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withOnlyHeroDefeated();
        Hero hero = HeroMother.createWithId(event.heroId());
        Hero heroUpdated = HeroMother.updatingHeroDefeated(hero, event.villainId());
        shouldSearch(hero);

        subscriber.on(event);

        shouldHaveSaved(heroUpdated);
    }

    @Test
    void no_change_status_when_is_not_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withNoneDefeated();
        Hero hero = HeroMother.createWithId(event.heroId());
        Hero heroNotUpdated = HeroMother.copy(hero);
        shouldSearch(hero);

        subscriber.on(event);

        shouldHaveSaved(heroNotUpdated);
    }

    @Test
    void no_change_status_when_is_already_defeated() {
        VersusCreatedEvent event = VersusCreatedEventMother.withOnlyHeroDefeated();
        Hero hero = HeroMother.createWithId(event.heroId());
        hero = HeroMother.updatingHeroDefeated(hero, event.villainId());
        Hero heroNotUpdated = HeroMother.copy(hero);
        shouldSearch(hero);

        subscriber.on(event);

        shouldHaveSaved(heroNotUpdated);
    }

    @Test
    void throw_an_exception_when_hero_not_found() {
        VersusCreatedEvent event = VersusCreatedEventMother.random();

        assertThrows(HeroNotFoundException.class, () -> subscriber.on(event));
    }

}
