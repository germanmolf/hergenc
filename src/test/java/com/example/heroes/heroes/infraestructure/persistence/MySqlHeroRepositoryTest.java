package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.*;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.PersistenceTestModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class MySqlHeroRepositoryTest extends PersistenceTestModule {

    @Autowired
    private HeroRepository repository;

    @Test
    void save_a_hero() {
        Hero hero = HeroMother.random();

        repository.save(hero);
    }

    @Test
    void return_an_existing_hero() {
        Hero hero = HeroMother.random();
        repository.save(hero);

        Optional<Hero> result = repository.search(hero.getId());

        assertEquals(Optional.of(hero), result);
    }

    @Test
    void not_return_a_non_existing_hero() {
        HeroId heroId = HeroIdMother.random();

        Optional<Hero> result = repository.search(heroId);

        assertFalse(result.isPresent());
    }

    @Test
    void search_all_heroes() {
        Hero aHero = HeroMother.random();
        Hero anotherHero = HeroMother.random();

        repository.save(aHero);
        repository.save(anotherHero);

        assertThat(Arrays.asList(aHero, anotherHero), containsInAnyOrder(repository.searchAll().toArray()));
    }

    @Test
    void search_heroes_using_criteria() {
        Hero matchingHero1 = HeroMother.create("Superman", "Superfuerza");
        Hero matchingHero2 = HeroMother.create("Spiderman", "Fuerza");
        Hero ironMan = HeroMother.create("Ironman", "Tecnología");
        Hero hulk = HeroMother.create("Hulk", "Superfuerza");

        Criteria criteria = HeroCriteriaMother.nameAndPowerContains("man", "fuerza");

        repository.save(matchingHero1);
        repository.save(matchingHero2);
        repository.save(ironMan);
        repository.save(hulk);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(matchingHero1, matchingHero2), containsInAnyOrder(result.toArray()));
    }
}
