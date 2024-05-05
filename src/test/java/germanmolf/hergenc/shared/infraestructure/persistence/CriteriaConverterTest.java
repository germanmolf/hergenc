package germanmolf.hergenc.shared.infraestructure.persistence;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroCriteriaMother;
import germanmolf.hergenc.heroes.domain.HeroMother;
import germanmolf.hergenc.heroes.infraestructure.persistence.MySqlHeroRepository;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInAnyOrder;

final class CriteriaConverterTest extends IntegrationTestModule {

    @Autowired
    private MySqlHeroRepository repository;

    @Test
    void search_by_equal() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("=", "Superman");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(List.of(superman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_not_equal() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("!=", "Superman");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(List.of(spiderman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_contains() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("contains", "super");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(List.of(superman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_not_contains() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.filterByName("not_contains", "super");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(List.of(spiderman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_in() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");
        Hero batman = HeroMother.create("Batman", "Tecnología");

        Criteria criteria = HeroCriteriaMother.filterByName("in", "Spiderman,Batman");

        repository.save(superman);
        repository.save(spiderman);
        repository.save(batman);

        List<Hero> result = repository.search(criteria);


        assertThat(List.of(spiderman, batman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_by_not_in() {
        Hero superman = HeroMother.create("Superman", "Volar");
        Hero spiderman = HeroMother.create("Spiderman", "Fuerza");
        Hero batman = HeroMother.create("Batman", "Tecnología");

        Criteria criteria = HeroCriteriaMother.filterByName("not_in", "Spiderman,Batman");

        repository.save(superman);
        repository.save(spiderman);
        repository.save(batman);

        List<Hero> result = repository.search(criteria);


        assertThat(List.of(superman), containsInAnyOrder(result.toArray()));
    }

    @Test
    void search_ordered_asc() {
        Hero superman = HeroMother.create("ASuperman", "Volar");
        Hero spiderman = HeroMother.create("BSpiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.orderByName("asc");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(List.of(superman, spiderman), contains(result.toArray()));
    }

    @Test
    void search_ordered_desc() {
        Hero superman = HeroMother.create("ASuperman", "Volar");
        Hero spiderman = HeroMother.create("BSpiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.orderByName("desc");

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);


        assertThat(List.of(spiderman, superman), contains(result.toArray()));
    }

    @Test
    void search_page() {
        Hero superman = HeroMother.create("ASuperman", "Volar");
        Hero spiderman = HeroMother.create("BSpiderman", "Fuerza");

        Criteria criteria = HeroCriteriaMother.withPaginationAndOrderByNameAsc(1, 0);

        repository.save(superman);
        repository.save(spiderman);


        List<Hero> result = repository.search(criteria);

        assertThat(List.of(superman), contains(result.toArray()));
    }
}
