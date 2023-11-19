package com.example.heroes.versus.infraestructure.persistence;

import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.persistence.PersistenceTestModule;
import com.example.heroes.versus.domain.*;
import com.example.heroes.villains.domain.VillainIdMother;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class VersusRepositoryMySqlTest extends PersistenceTestModule {

    @Autowired
    private VersusRepository repository;

    @Test
    void save_a_versus() {
        Versus versus = VersusMother.random();

        repository.save(versus);
    }

    @Test
    void return_an_existing_versus() {
        Versus versus = VersusMother.random();
        repository.save(versus);

        Optional<Versus> result = repository.search(versus.getId());

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
        String heroIdMatching = HeroIdMother.random().value();
        String villainIdMatching = VillainIdMother.random().value();
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
        String heroIdMatching = HeroIdMother.random().value();
        String villainIdMatching = VillainIdMother.random().value();
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
