package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.HeroModuleTest;
import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroCreatedEvent;
import com.example.heroes.heroes.domain.HeroCreatedEventMother;
import com.example.heroes.heroes.domain.HeroMother;
import com.example.heroes.heroes.domain.exceptions.*;
import com.example.heroes.shared.domain.exceptions.IdentifierNotValidException;
import com.example.heroes.shared.domain.exceptions.IdentifierNullException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

final class HeroCreatorTest extends HeroModuleTest {

    private HeroCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        creator = new HeroCreator(repository, eventBus);
    }

    @Test
    void create_a_valid_hero() {
        CreateHeroRequest request = CreateHeroRequestMother.random();
        Hero hero = HeroMother.fromRequest(request);
        HeroCreatedEvent event = HeroCreatedEventMother.fromAggregate(hero);

        creator.create(request);

        shouldHaveSaved(hero);
        shouldHavePublished(event);
    }

    @Test
    void throw_an_exception_when_id_is_null() {
        CreateHeroRequest request = CreateHeroRequestMother.withIdNull();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(IdentifierNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_id_is_not_valid() {
        CreateHeroRequest request = CreateHeroRequestMother.withIdNotValid();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(IdentifierNotValidException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_name_is_null() {
        CreateHeroRequest request = CreateHeroRequestMother.withNameNull();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(HeroNameNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_name_is_blank() {
        CreateHeroRequest request = CreateHeroRequestMother.withNameBlank();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(HeroNameInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_name_is_over_length() {
        CreateHeroRequest request = CreateHeroRequestMother.withNameOverLength();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(HeroNameInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_power_is_null() {
        CreateHeroRequest request = CreateHeroRequestMother.withPowerNull();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(HeroPowerNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_power_is_blank() {
        CreateHeroRequest request = CreateHeroRequestMother.withPowerBlank();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(HeroPowerInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_power_is_over_length() {
        CreateHeroRequest request = CreateHeroRequestMother.withPowerOverLength();
        HeroCreatedEvent event = HeroCreatedEventMother.fromRequest(request);

        assertThrows(HeroPowerInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_hero_already_exists() {
        Hero hero = HeroMother.random();
        shouldSearch(hero);
        CreateHeroRequest requestRepeated = CreateHeroRequestMother.fromAggregate(hero);
        Hero heroRepeated = HeroMother.fromRequest(requestRepeated);
        HeroCreatedEvent eventRepeated = HeroCreatedEventMother.fromAggregate(heroRepeated);

        assertThrows(HeroAlreadyExistsException.class, () -> creator.create(requestRepeated));

        shouldNotHaveSaved(heroRepeated);
        shouldNotHavePublished(eventRepeated);
    }
}
