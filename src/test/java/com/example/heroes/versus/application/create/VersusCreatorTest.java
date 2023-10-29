package com.example.heroes.versus.application.create;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroMother;
import com.example.heroes.heroes.domain.exceptions.HeroNotFoundException;
import com.example.heroes.versus.application.VersusModuleTest;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusCreatedEventMother;
import com.example.heroes.versus.domain.VersusMother;
import com.example.heroes.versus.domain.exceptions.HeroAlreadyDefeatedException;
import com.example.heroes.versus.domain.exceptions.VersusDefeatedInvalidValueException;
import com.example.heroes.versus.domain.exceptions.VersusDefeatedNullException;
import com.example.heroes.versus.domain.exceptions.VillainAlreadyDefeatedException;
import com.example.heroes.villains.application.find.VillainFinder;
import com.example.heroes.villains.domain.Villain;
import com.example.heroes.villains.domain.VillainMother;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

//un caso de uso para sumar los que ha derrotado y otro para ponerlo como derrotado
//el listener que sea quien compruebe si hay que llamar al defeater
//probar si hay condiciones de carreara si dos listener modifican la misma entidad
public final class VersusCreatorTest extends VersusModuleTest {

    private VersusCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        creator = new VersusCreator(repository, eventBus, new HeroFinder(heroRepository), new VillainFinder(villainRepository));
    }

    @Test
    void create_a_valid_versus() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        Hero hero = HeroMother.create(versus.getHeroId().value());
        Villain villain = VillainMother.create(versus.getVillainId().value());
        shouldSearchHero(hero);
        shouldSearchVillain(villain);

        creator.create(request);

        shouldHaveSaved(versus);
        shouldHavePublished(event);
    }

    @Test
    void throw_an_exception_when_versus_already_exists() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        Versus versusExists = VersusMother.fromRequest(request);
        shouldSearch(versusExists);
        
        assertThrows(VersusAlreadyExistsException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_defeated_is_null() {
        CreateVersusRequest request = CreateVersusRequestMother.withDefeatedNull();
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);

        assertThrows(VersusDefeatedNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_defeated_value_is_not_valid() {
        CreateVersusRequest request = CreateVersusRequestMother.withDefeatedNotValid();
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);

        assertThrows(VersusDefeatedInvalidValueException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_hero_not_found() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        Villain villain = VillainMother.create(versus.getVillainId().value());
        shouldSearchVillain(villain);

        assertThrows(HeroNotFoundException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_villain_not_found() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        Hero hero = HeroMother.create(versus.getHeroId().value());
        shouldSearchHero(hero);

        assertThrows(VillainNotFoundException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_hero_is_already_defeated() {
        CreateVersusRequest request = CreateVersusRequestMother.withHeroDefeated();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        Versus versusWithHeroDefeated = VersusMother.fromRequest(CreateVersusRequestMother.withHeroDefeated());
        Hero hero = HeroMother.create(versus.getHeroId().value());
        Villain villain = VillainMother.create(versus.getVillainId().value());

        shouldSearch(List.of(versusWithHeroDefeated));
        shouldSearchHero(hero);
        shouldSearchVillain(villain);

        assertThrows(HeroAlreadyDefeatedException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_villain_is_already_defeated() {
        CreateVersusRequest request = CreateVersusRequestMother.withVillainDefeated();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        Versus versusWithVillainDefeated = VersusMother.fromRequest(CreateVersusRequestMother.withVillainDefeated());
        Hero hero = HeroMother.create(versus.getHeroId().value());
        Villain villain = VillainMother.create(versus.getVillainId().value());

        shouldSearch(new ArrayList<>(), List.of(versusWithVillainDefeated));
        shouldSearchHero(hero);
        shouldSearchVillain(villain);

        assertThrows(VillainAlreadyDefeatedException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }
}
