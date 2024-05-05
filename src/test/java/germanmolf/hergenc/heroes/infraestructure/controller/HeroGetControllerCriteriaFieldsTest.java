package germanmolf.hergenc.heroes.infraestructure.controller;

import germanmolf.hergenc.heroes.application.find.HeroFinder;
import germanmolf.hergenc.heroes.application.find.HeroSearcher;
import germanmolf.hergenc.shared.domain.CriteriaMother;
import germanmolf.hergenc.shared.infraestructure.controller.ControllerCriteriaFieldsTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public final class HeroGetControllerCriteriaFieldsTest extends ControllerCriteriaFieldsTest {

    private HeroGetController controller;
    private HeroSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        HeroFinder finder = mock(HeroFinder.class);
        searcher = mock(HeroSearcher.class);
        controller = new HeroGetController(finder, searcher);
    }

    @Test
    void allows_filter_by_name() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("name", "contains", "value");

        controller.getHeroes(params);

        shouldFilterBy("name");
    }

    @Test
    void allows_filter_by_power() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("power", "contains", "value");

        controller.getHeroes(params);

        shouldFilterBy("power");
    }

    @Test
    void allows_filter_by_villains_defeated_total() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("villainsDefeatedTotal", "contains",
                "value");

        controller.getHeroes(params);

        shouldFilterBy("villainsDefeatedTotal");
    }

    @Test
    void allows_filter_by_status() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("status", "contains", "value");

        controller.getHeroes(params);

        shouldFilterBy("status");
    }

    @Test
    void allows_filter_by_villain_defeater() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("villainDefeater", "contains", "value");

        controller.getHeroes(params);

        shouldFilterBy("villainDefeater");
    }

    @Test
    void ignore_not_allowed_filter() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("qwe", "contains", "value");

        controller.getHeroes(params);

        shouldNoFilterBy("qwe");
    }

    @Test
    void allows_order_by_name() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("name", "asc");

        controller.getHeroes(params);

        shouldOrderBy("name");
    }

    @Test
    void allows_order_by_power() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("power", "asc");

        controller.getHeroes(params);

        shouldOrderBy("power");
    }

    @Test
    void allows_order_by_villains_defeated_total() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("villainsDefeatedTotal", "asc");

        controller.getHeroes(params);

        shouldOrderBy("villainsDefeatedTotal");
    }

    @Test
    void allows_order_by_status() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("status", "asc");

        controller.getHeroes(params);

        shouldOrderBy("status");
    }

    @Test
    void ignore_not_allowed_orderBy() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("qwe", "contains", "value");

        controller.getHeroes(params);

        shouldNotOrderBy("qwe");
    }

    private void shouldFilterBy(String field) {
        verify(searcher).search(argument.capture());
        criteriaHasFilter(field);
    }

    private void shouldNoFilterBy(String field) {
        verify(searcher).search(argument.capture());
        criteriaHasNoFilter(field);
    }

    private void shouldOrderBy(String orderBy) {
        verify(searcher).search(argument.capture());
        criteriaHasOrderBy(orderBy);
    }

    private void shouldNotOrderBy(String orderBy) {
        verify(searcher).search(argument.capture());
        criteriaHasNoOrderBy(orderBy);
    }
}
