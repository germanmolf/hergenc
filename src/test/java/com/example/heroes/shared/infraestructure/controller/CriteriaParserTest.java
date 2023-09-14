package com.example.heroes.shared.infraestructure.controller;

import com.example.heroes.shared.application.UnitTestModule;
import com.example.heroes.shared.domain.CriteriaMother;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.criteria.FilterOperator;
import com.example.heroes.shared.domain.criteria.OrderType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

final class CriteriaParserTest extends UnitTestModule {

    private Criteria rawCriteria;

    @BeforeEach
    protected void setUp() {
        rawCriteria = new Criteria();
    }

    @Test
    void create_with_valid_filter() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("name", "contains", "value");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getFilters(), contains(allOf(
                hasProperty("field", is("name")),
                hasProperty("operator", is(FilterOperator.CONTAINS)),
                hasProperty("value", is("value")))
        ));
    }

    @Test
    void create_with_no_filters() {
        Map<String, String> params = CriteriaMother.createEmptyParams();

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getFilters(), is(rawCriteria.getFilters()));
    }

    @Test
    void no_parse_a_filter_with_no_field() {
        Map<String, String> params = CriteriaMother.createParamsWithFilterWithNoField("contains", "value");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getFilters(), is(rawCriteria.getFilters()));
    }

    @Test
    void no_parse_a_filter_with_no_value() {
        Map<String, String> params = CriteriaMother.createParamsWithFilterWithNoValue("name", "contains");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getFilters(), is(rawCriteria.getFilters()));
    }

    @Test
    void no_parse_a_filter_with_no_field_value() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("", "contains", "value");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getFilters(), is(rawCriteria.getFilters()));
    }

    @Test
    void no_parse_a_filter_with_no_value_value() {
        Map<String, String> params = CriteriaMother.createParamsWithFilter("name", "contains", "");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getFilters(), is(rawCriteria.getFilters()));
    }

    @Test
    void create_with_valid_order() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("name", "asc");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getOrder(), allOf(
                hasProperty("orderBy", is("name")),
                hasProperty("orderType", is(OrderType.ASC))
        ));
    }

    @Test
    void create_with_no_order() {
        Map<String, String> params = CriteriaMother.createEmptyParams();

        Criteria criteria = CriteriaParser.fromParams(params);

        assertFalse(criteria.getOrder().hasOrder());
    }

    @Test
    void no_parse_a_order_with_no_orderBy_value() {
        Map<String, String> params = CriteriaMother.createParamsWithOrder("", "asc");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertFalse(criteria.getOrder().hasOrder());
    }

    @Test
    void create_with_valid_limit() {
        Map<String, String> params = CriteriaMother.createParamsWithLimit("1");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getLimit(), is(1));
    }

    @Test
    void create_with_no_limit() {
        Map<String, String> params = CriteriaMother.createEmptyParams();

        Criteria criteria = CriteriaParser.fromParams(params);

        assertEquals(rawCriteria.getLimit(), criteria.getLimit());
    }

    @Test
    void no_parse_limit_with_no_value() {
        Map<String, String> params = CriteriaMother.createParamsWithLimit("");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getLimit(), is(rawCriteria.getLimit()));
    }

    @Test
    void no_parse_limit_with_no_valid_value() {
        Map<String, String> params = CriteriaMother.createParamsWithLimit("qwe");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getLimit(), is(rawCriteria.getLimit()));
    }

    @Test
    void create_with_valid_start() {
        Map<String, String> params = CriteriaMother.createParamsWithStart("1");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getStart(), is(1));
    }

    @Test
    void create_with_no_start() {
        Map<String, String> params = CriteriaMother.createEmptyParams();

        Criteria criteria = CriteriaParser.fromParams(params);

        assertEquals(rawCriteria.getStart(), criteria.getStart());
    }

    @Test
    void no_parse_start_with_no_value() {
        Map<String, String> params = CriteriaMother.createParamsWithStart("");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getStart(), is(rawCriteria.getStart()));
    }

    @Test
    void no_parse_start_with_no_valid_value() {
        Map<String, String> params = CriteriaMother.createParamsWithStart("qwe");

        Criteria criteria = CriteriaParser.fromParams(params);

        assertThat(criteria.getStart(), is(rawCriteria.getStart()));
    }
}
