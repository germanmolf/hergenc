package germanmolf.hergenc.versus.infraestructure.controller;

import germanmolf.hergenc.shared.domain.CriteriaMother;
import germanmolf.hergenc.shared.infraestructure.controller.ControllerCriteriaFieldsTest;
import germanmolf.hergenc.versus.application.find.VersusFinder;
import germanmolf.hergenc.versus.application.find.VersusSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public final class VersusGetControllerCriteriaFieldsTest extends ControllerCriteriaFieldsTest {

    private VersusGetController controller;
    private VersusSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        VersusFinder finder = mock(VersusFinder.class);
        searcher = mock(VersusSearcher.class);
        controller = new VersusGetController(finder, searcher);
    }

    @Test
    void allows_filter_by_hero_id() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("heroId", "contains", "value");

        controller.getVersus(params);

        shouldFilterBy("heroId");
    }

    @Test
    void allows_filter_by_villain_id() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("villainId", "contains", "value");

        controller.getVersus(params);

        shouldFilterBy("villainId");
    }

    @Test
    void allows_filter_by_defeated() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("defeated", "contains",
                "value");

        controller.getVersus(params);

        shouldFilterBy("defeated");
    }

    @Test
    void ignore_not_allowed_filter() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("qwe", "contains", "value");

        controller.getVersus(params);

        shouldNoFilterBy("qwe");
    }

    @Test
    void allows_order_by_hero_id() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("heroId", "asc");

        controller.getVersus(params);

        shouldOrderBy("heroId");
    }

    @Test
    void allows_order_by_villain_id() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("villainId", "asc");

        controller.getVersus(params);

        shouldOrderBy("villainId");
    }

    @Test
    void allows_order_by_defeated() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("defeated", "asc");

        controller.getVersus(params);

        shouldOrderBy("defeated");
    }

    @Test
    void ignore_not_allowed_orderBy() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("qwe", "contains", "value");

        controller.getVersus(params);

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
