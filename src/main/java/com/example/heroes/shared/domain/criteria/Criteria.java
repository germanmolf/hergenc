package com.example.heroes.shared.domain.criteria;

import java.io.Serializable;
import java.util.HashMap;

public final class Criteria {

    private final Filters filters;
    private final Order order;
    private final Integer limit;
    private final Integer start;

    public Criteria(Filters filters, Order order, Integer limit, Integer start) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.start = start;
    }

    public Criteria(Filters filters, Order order) {
        this.filters = filters;
        this.order = order;
        this.limit = 100;
        this.start = 0;
    }

    public static Criteria fromValues(HashMap<String, Serializable> values) {
        return new Criteria(Filters.fromValues(values), Order.fromValues(values), (Integer) values.get("limit"), (Integer) values.get("start"));
    }

    public Filters getFilters() {
        return filters;
    }

    public Order getOrder() {
        return order;
    }

    public Integer getLimit() {
        return limit;
    }

    public Integer getStart() {
        return start;
    }

}
