package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.application.find.HeroSearcher;
import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.shared.domain.CriteriaMother;
import com.example.heroes.shared.domain.criteria.Criteria;
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

public final class HeroGetControllerCriteriaFieldsTest extends UnitTestModule {

    private HeroGetController controller;
    private HeroSearcher searcher;
    private ArgumentCaptor<Criteria> argument;

    @BeforeEach
    protected void setUp() {
        HeroFinder finder = mock(HeroFinder.class);
        searcher = mock(HeroSearcher.class);
        controller = new HeroGetController(finder, searcher);
        argument = ArgumentCaptor.forClass(Criteria.class);
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
