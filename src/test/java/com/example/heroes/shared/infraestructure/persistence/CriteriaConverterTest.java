package com.example.heroes.shared.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroCriteriaMother;
import com.example.heroes.heroes.domain.HeroMother;
import com.example.heroes.heroes.domain.HeroRepository;
import com.example.heroes.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

final class CriteriaConverterTest extends PersistenceTestModule {

    @Autowired
    private HeroRepository repository;

    @Test
    void search_by_equal() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("=", "Superman");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(superman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_not_equal() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("!=", "Superman");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(spiderman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_contains() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("contains", "super");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(superman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_not_contains() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("not_contains", "super");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(spiderman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_ordered_asc() {
        Hero superman = HeroMother.create("ASuperman", "Volar");
        Hero spiderman = HeroMother.create("BSpiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.orderByName("asc");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(superman, spiderman), contains(result.toArray()));
    }

    @Test
    void search_ordered_desc() {
        Hero superman = HeroMother.create("ASuperman", "Volar");
        Hero spiderman = HeroMother.create("BSpiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.orderByName("desc");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(Arrays.asList(spiderman, superman), contains(result.toArray()));
    }

    @Test
    void search_page() {
        Hero superman = HeroMother.create("ASuperman", "Volar");
        Hero spiderman = HeroMother.create("BSpiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.withPaginationAndOrderByNameAsc(1, 0);

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);

        assertThat(Arrays.asList(superman), contains(result.toArray()));
    }
}
