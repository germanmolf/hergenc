package germanmolf.hergenc.villains.infraestructure.persistence;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.infraestructure.persistence.IntegrationTestModule;
import germanmolf.hergenc.villains.domain.VillainCriteriaMother;
import germanmolf.hergenc.villains.domain.VillainIdMother;
import germanmolf.hergenc.villains.domain.VillainMother;
import germanmolf.hergenc.villains.domain.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class MySqlVillainRepositoryTest extends IntegrationTestModule {

    @Autowired
    private MySqlVillainRepository repository;

    @Test
    void save_a_villain() {
        Villain villain = VillainMother.random();

        repository.save(villain);
    }

    @Test
    void return_an_existing_villain() {
        Villain villain = VillainMother.random();
        repository.save(villain);

        Optional<Villain> result = repository.search(villain.id());

        assertEquals(Optional.of(villain), result);
    }

    @Test
    void not_return_a_non_existing_villain() {
        VillainId heroId = VillainIdMother.random();

        Optional<Villain> result = repository.search(heroId);

        assertFalse(result.isPresent());
    }

    @Test
    void search_villains_using_criteria() {
        Villain matchingVillain1 = VillainMother.create("Joker", "Está loco");
        Villain matchingVillain2 = VillainMother.create("Duende verde", "Otro loco");
        Villain criptonita = VillainMother.create("Criptonita", "Piedra");
        Villain venom = VillainMother.create("Venom", "Spiderman malo");

        Criteria criteria = VillainCriteriaMother.nameAndPowerContains("e", "loco");

        repository.save(matchingVillain1);
        repository.save(matchingVillain2);
        repository.save(criptonita);
        repository.save(venom);


        List<Villain> result = repository.search(criteria);


        assertThat(List.of(matchingVillain1, matchingVillain2), containsInAnyOrder(result.toArray()));
    }
}
