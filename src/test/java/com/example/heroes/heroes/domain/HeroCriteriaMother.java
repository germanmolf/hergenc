package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.criteria.Filter;
import com.example.heroes.shared.domain.criteria.Filters;
import com.example.heroes.shared.domain.criteria.Order;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

public final class HeroCriteriaMother {

    public static Criteria filterByName(String operator, String value) {
        Filter filter = new Filter("name", operator, value);
        return new Criteria(new Filters(Arrays.asList(filter)), Order.none());
    }

    public static Criteria orderByName(String orderType) {
        Order order = new Order("name", orderType);
        return new Criteria(new Filters(Collections.emptyList()), order);
    }

    public static Criteria nameAndPowerContains(String name, String power) {
        Filter nameFilter = new Filter("name", "contains", name);
        Filter powerFilter = new Filter("power", "contains", power);
        return new Criteria(new Filters(Arrays.asList(nameFilter, powerFilter)), Order.none());
    }

    public static Criteria withPaginationAndOrderByNameAsc(Integer limit, Integer start) {
        Order order = new Order("name", "asc");
        return new Criteria(new Filters(Collections.emptyList()), order, Optional.of(limit), Optional.of(start));
    }
}
