package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.criteria.Filter;

import java.util.Arrays;
import java.util.List;

public final class HeroCriteriaMother {

    public static Criteria filterByName(String operator, String value) {
        Filter filter = new Filter("name", operator, value);
        List<Filter> filters = Arrays.asList(filter);
        return new Criteria().setFilters(filters);
    }

    public static Criteria orderByName(String orderType) {
        return new Criteria().setOrder("name", orderType);
    }

    public static Criteria nameAndPowerContains(String name, String power) {
        Filter nameFilter = new Filter("name", "contains", name);
        Filter powerFilter = new Filter("power", "contains", power);
        List<Filter> filters = Arrays.asList(nameFilter, powerFilter);
        return new Criteria().setFilters(filters);
    }

    public static Criteria withPaginationAndOrderByNameAsc(Integer limit, Integer start) {
        return new Criteria()
                .setOrder("name", "asc")
                .setLimit(limit)
                .setStart(start);
    }
}
