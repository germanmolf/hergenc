package com.example.heroes.versus.application.create;

import com.example.heroes.heroes.domain.exceptions.HeroNotFoundException;
import com.example.heroes.shared.domain.LongMother;
import com.example.heroes.shared.domain.exceptions.IdentifierNotValidException;
import com.example.heroes.shared.domain.exceptions.IdentifierNullException;
import com.example.heroes.versus.application.VersusModuleTest;
import com.example.heroes.versus.domain.Versus;
import com.example.heroes.versus.domain.VersusCreatedEvent;
import com.example.heroes.versus.domain.VersusCreatedEventMother;
import com.example.heroes.versus.domain.VersusMother;
import com.example.heroes.versus.domain.exceptions.*;
import com.example.heroes.villains.domain.exceptions.VillainNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public final class VersusCreatorTest extends VersusModuleTest {

    private VersusCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        creator = new VersusCreator(repository, eventBus, heroFinder, villainFinder);
    }

    @Test
    void create_a_valid_versus() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);

        creator.create(request);

        shouldHaveSaved(versus);
        shouldHavePublished(event);
    }


    @Test
    void throw_an_exception_when_id_is_null() {
        CreateVersusRequest request = CreateVersusRequestMother.withIdNull();
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);

        IdentifierNullException exception = assertThrows(IdentifierNullException.class, () -> creator.create(request));

        assertEquals("versus_identifier_null", exception.getErrorCode());
        assertEquals("The versus identifier is null", exception.getErrorMessage());
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_id_is_not_valid() {
        CreateVersusRequest request = CreateVersusRequestMother.withIdNotValid();
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);

        IdentifierNotValidException exception = assertThrows(IdentifierNotValidException.class, () -> creator.create(request));

        assertEquals("versus_identifier_not_valid", exception.getErrorCode());
        assertEquals(String.format("The versus identifier <%s> is not a valid UUID", request.id()),
                exception.getErrorMessage());
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
    void throw_an_exception_when_hero_not_found() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        shouldNotFindHero(versus.getHeroId());

        assertThrows(HeroNotFoundException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_villain_not_found() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        shouldNotFindVillain(versus.getVillainId());

        assertThrows(VillainNotFoundException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_hero_is_already_defeated() {
        CreateVersusRequest request = CreateVersusRequestMother.withHeroDefeated();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        shouldCount(LongMother.greaterThanZero(), 0L);

        assertThrows(HeroAlreadyDefeatedException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_villain_is_already_defeated() {
        CreateVersusRequest request = CreateVersusRequestMother.withVillainDefeated();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        shouldCount(0L, LongMother.greaterThanZero());

        assertThrows(VillainAlreadyDefeatedException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }
}
