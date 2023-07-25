package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.HeroModuleTest;
import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroCreated;
import com.example.heroes.heroes.domain.HeroCreatedMother;
import com.example.heroes.heroes.domain.HeroMother;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
        HeroCreated event = HeroCreatedMother.fromHero(hero);

        creator.create(request);

        shouldHaveSaved(hero);
        shouldHavePublished(event);
    }
}
