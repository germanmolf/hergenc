package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroMother;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.infraestructure.persistence.PersistenceTestModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

class MySqlHeroRepositoryTest extends PersistenceTestModule {

    @Autowired
    private HeroRepository repository;

    @Test
    void save_a_hero() {
        repository.save(HeroMother.random());
    }

    @Test
    void search_all_heroes() {
        Hero aHero = HeroMother.random();
        Hero anotherHero = HeroMother.random();

        repository.save(aHero);
        repository.save(anotherHero);

        assertThat(Arrays.asList(aHero, anotherHero), containsInAnyOrder(repository.searchAll().toArray()));
    }

}
