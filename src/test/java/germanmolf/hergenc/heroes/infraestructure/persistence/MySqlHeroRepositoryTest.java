package germanmolf.hergenc.heroes.infraestructure.persistence;

import germanmolf.hergenc.heroes.domain.*;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.persistence.IntegrationTestModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class MySqlHeroRepositoryTest extends IntegrationTestModule {

    @Autowired
    private MySqlHeroRepository repository;

    @Test
    void save_a_hero() {
        Hero hero = HeroMother.random();

        repository.save(hero);
    }

    @Test
    void return_an_existing_hero() {
        Hero hero = HeroMother.random();
        repository.save(hero);

        Optional<Hero> result = repository.search(hero.id());

        assertEquals(Optional.of(hero), result);
    }

    @Test
    void not_return_a_non_existing_hero() {
        HeroId heroId = HeroIdMother.random();

        Optional<Hero> result = repository.search(heroId);

        assertFalse(result.isPresent());
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


        assertThat(List.of(matchingHero1, matchingHero2), containsInAnyOrder(result.toArray()));
    }
}
