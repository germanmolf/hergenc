package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.shared.domain.CriteriaMother;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.versus.application.find.VersusFinder;
import com.example.heroes.versus.application.find.VersusSearcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public final class VersusGetControllerCriteriaFieldsTest extends UnitTestModule {

    private VersusGetController controller;
    private VersusSearcher searcher;
    private ArgumentCaptor<Criteria> argument;

    @BeforeEach
    protected void setUp() {
        VersusFinder finder = mock(VersusFinder.class);
        searcher = mock(VersusSearcher.class);
        controller = new VersusGetController(finder, searcher);
        argument = ArgumentCaptor.forClass(Criteria.class);
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
        assertThat(argument.getValue().getFilters(), contains(hasProperty("field", is(field))));
    }

    private void shouldNoFilterBy(String field) {
        verify(searcher).search(argument.capture());
        assertThat(argument.getValue().getFilters(), not(contains(hasProperty("field", is(field)))));
    }

    private void shouldOrderBy(String orderBy) {
        verify(searcher).search(argument.capture());
        assertThat(argument.getValue().getOrder().getOrderBy(), is(orderBy));
    }

    private void shouldNotOrderBy(String orderBy) {
        verify(searcher).search(argument.capture());
        assertThat(argument.getValue().getOrder().getOrderBy(), is(not(orderBy)));
    }
}
