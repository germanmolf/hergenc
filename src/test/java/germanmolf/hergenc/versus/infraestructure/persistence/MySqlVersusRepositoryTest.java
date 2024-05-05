package germanmolf.hergenc.versus.infraestructure.persistence;

import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.persistence.IntegrationTestModule;
import germanmolf.hergenc.versus.domain.*;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class MySqlVersusRepositoryTest extends IntegrationTestModule {

    @Autowired
    private MySqlVersusRepository repository;

    @Test
    void save_a_versus() {
        Versus versus = VersusMother.random();

        repository.save(versus);
    }

    @Test
    void return_an_existing_versus() {
        Versus versus = VersusMother.random();
        repository.save(versus);

        Optional<Versus> result = repository.search(versus.id());

        assertEquals(Optional.of(versus), result);
    }

    @Test
    void not_return_a_non_existing_versus() {
        VersusId versusId = VersusIdMother.random();

        Optional<Versus> result = repository.search(versusId);

        assertFalse(result.isPresent());
    }

    @Test
    void search_versus_using_criteria() {
        String heroIdMatching = HeroIdMother.randomValue();
        String villainIdMatching = VillainIdMother.randomValue();
        Versus matchingVersus1 = VersusMother.create(heroIdMatching, villainIdMatching, VersusDefeated.NONE.value());
        Versus matchingVersus2 = VersusMother.create(heroIdMatching, villainIdMatching, VersusDefeated.NONE.value());
        Versus noMatchingVersus1 = VersusMother.random();
        Versus noMatchingVersus2 = VersusMother.random();

        Criteria criteria = VersusCriteriaMother.heroAndVillainNoneDefeated(heroIdMatching, villainIdMatching);

        repository.save(matchingVersus1);
        repository.save(matchingVersus2);
        repository.save(noMatchingVersus1);
        repository.save(noMatchingVersus2);


        List<Versus> result = repository.search(criteria);


        assertThat(List.of(matchingVersus1, matchingVersus2), containsInAnyOrder(result.toArray()));
    }

    @Test
    void count_versus_using_criteria() {
        String heroIdMatching = HeroIdMother.randomValue();
        String villainIdMatching = VillainIdMother.randomValue();
        Versus matchingVersus1 = VersusMother.create(heroIdMatching, villainIdMatching, VersusDefeated.NONE.value());
        Versus matchingVersus2 = VersusMother.create(heroIdMatching, villainIdMatching, VersusDefeated.NONE.value());
        Versus noMatchingVersus1 = VersusMother.random();
        Versus noMatchingVersus2 = VersusMother.random();

        Criteria criteria = VersusCriteriaMother.heroAndVillainNoneDefeated(heroIdMatching, villainIdMatching);

        repository.save(matchingVersus1);
        repository.save(matchingVersus2);
        repository.save(noMatchingVersus1);
        repository.save(noMatchingVersus2);


        Long count = repository.count(criteria);


        assertEquals(List.of(matchingVersus1, matchingVersus2).size(), count);
    }
}
