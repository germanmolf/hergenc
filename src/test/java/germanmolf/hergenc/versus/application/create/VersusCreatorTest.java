package germanmolf.hergenc.versus.application.create;

import germanmolf.hergenc.heroes.domain.exceptions.HeroNotFoundException;
import germanmolf.hergenc.shared.domain.LongMother;
import germanmolf.hergenc.shared.domain.exceptions.IdentifierNotValidException;
import germanmolf.hergenc.shared.domain.exceptions.IdentifierNullException;
import germanmolf.hergenc.versus.application.VersusModuleTest;
import germanmolf.hergenc.versus.domain.Versus;
import germanmolf.hergenc.versus.domain.VersusCreatedEvent;
import germanmolf.hergenc.versus.domain.VersusCreatedEventMother;
import germanmolf.hergenc.versus.domain.VersusMother;
import germanmolf.hergenc.versus.domain.exceptions.*;
import germanmolf.hergenc.villains.domain.exceptions.VillainNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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

        assertThrows(IdentifierNullException.class, () -> creator.create(request));

        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_id_is_not_valid() {
        CreateVersusRequest request = CreateVersusRequestMother.withIdNotValid();
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);

        assertThrows(IdentifierNotValidException.class, () -> creator.create(request));

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
        shouldNotFindHero(versus.heroId());

        assertThrows(HeroNotFoundException.class, () -> creator.create(request));

        shouldNotHaveSaved(versus);
        shouldNotHavePublished(event);
    }

    @Test
    void throw_an_exception_when_villain_not_found() {
        CreateVersusRequest request = CreateVersusRequestMother.random();
        Versus versus = VersusMother.fromRequest(request);
        VersusCreatedEvent event = VersusCreatedEventMother.fromRequest(request);
        shouldNotFindVillain(versus.villainid());

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
