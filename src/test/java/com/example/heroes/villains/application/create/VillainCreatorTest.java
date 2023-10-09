package com.example.heroes.villains.application.create;

import com.example.heroes.villains.VillainModuleTest;
import com.example.heroes.villains.domain.*;
import com.example.heroes.villains.domain.exceptions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;


public final class VillainCreatorTest extends VillainModuleTest {

    private VillainCreator creator;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        creator = new VillainCreator(repository, eventBus);
    }

    @Test
    void create_a_valid_villain() {
        CreateVillainRequest request = CreateVillainRequestMother.random();
        Villain villain = VillainMother.fromRequest(request);
        VillainCreatedEvent event = VillainCreatedEventMother.fromAggregate(villain);

        creator.create(request);

        shouldHaveSaved(villain);
        shouldHavePublished(event);
    }

    @Test
    void throw_an_exception_when_villain_already_exists() {
        Villain villain = VillainMother.random();
        shouldSearch(villain);
        CreateVillainRequest requestRepeated = CreateVillainRequestMother.fromAggregate(villain);
        Villain villainRepeated = VillainMother.fromRequest(requestRepeated);
        VillainCreatedEvent eventRepeated = VillainCreatedEventMother.fromAggregate(villainRepeated);

        assertThrows(VillainAlreadyExistsException.class, () -> creator.create(requestRepeated));

        shouldNotHaveSaved(villainRepeated);
        shouldNotHavePublished(eventRepeated);
    }

    @Test
    void throw_an_exception_when_name_is_null() {
        CreateVillainRequest request = CreateVillainRequestMother.create(VillainIdMother.randomValue(), null, VillainPowerMother.random());
        VillainCreatedEvent event = VillainCreatedEventMother.fromRequest(request);

        assertThrows(VillainNameNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_name_is_blank() {
        CreateVillainRequest request = CreateVillainRequestMother.create(VillainIdMother.randomValue(), "", VillainPowerMother.random());
        VillainCreatedEvent event = VillainCreatedEventMother.fromRequest(request);

        assertThrows(VillainNameInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_name_is_over_length() {
        CreateVillainRequest request = CreateVillainRequestMother.create(VillainIdMother.randomValue(), "villainnamevillainname", VillainPowerMother.random());
        VillainCreatedEvent event = VillainCreatedEventMother.fromRequest(request);

        assertThrows(VillainNameInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_power_is_null() {
        CreateVillainRequest request = CreateVillainRequestMother.create(VillainIdMother.randomValue(), VillainNameMother.random(), null);
        VillainCreatedEvent event = VillainCreatedEventMother.fromRequest(request);

        assertThrows(VillainPowerNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_power_is_blank() {
        CreateVillainRequest request = CreateVillainRequestMother.create(VillainIdMother.randomValue(), VillainNameMother.random(), "");
        VillainCreatedEvent event = VillainCreatedEventMother.fromRequest(request);

        assertThrows(VillainPowerInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_power_is_over_length() {
        CreateVillainRequest request = CreateVillainRequestMother.create(VillainIdMother.randomValue(), VillainNameMother.random(), "villainpowervillainpowervillain");
        VillainCreatedEvent event = VillainCreatedEventMother.fromRequest(request);

        assertThrows(VillainPowerInvalidLengthException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }
}
