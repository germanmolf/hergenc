package com.example.heroes.shared.infraestructure.controller;

import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentCaptor;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;

public class ControllerCriteriaFieldsTest extends UnitTestModule {

    protected ArgumentCaptor<Criteria> argument;

    @BeforeEach
    protected void setUp() {
        argument = ArgumentCaptor.forClass(Criteria.class);
    }

    protected void criteriaHasFilter(String field) {
        assertThat(argument.getValue().getFilters(), contains(hasProperty("field", is(field))));
    }

    protected void criteriaHasNoFilter(String field) {
        assertThat(argument.getValue().getFilters(), not(contains(hasProperty("field", is(field)))));
    }

    protected void criteriaHasOrderBy(String orderBy) {
        assertThat(argument.getValue().getOrder().getOrderBy(), is(orderBy));
    }

    protected void criteriaHasNoOrderBy(String orderBy) {
        assertThat(argument.getValue().getOrder().getOrderBy(), is(not(orderBy)));
    }
}
