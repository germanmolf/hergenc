package com.example.heroes.shared.domain.criteria;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Optional;

public final class Criteria {

    private final Filters filters;
    private final Order order;
    private final Optional<Integer> limit;
    private final Optional<Integer> start;

    public Criteria(Filters filters, Order order, Optional<Integer> limit, Optional<Integer> start) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.start = start;
    }

    public Criteria(Filters filters, Order order) {
        this.filters = filters;
        this.order = order;
        this.limit = Optional.empty();
        this.start = Optional.empty();
    }

    public static Criteria fromValues(HashMap<String, Serializable> values) {
        return new Criteria(
                Filters.fromValues(values),
                Order.fromValues(values),
                Optional.ofNullable((Integer) values.get("limit")),
                Optional.ofNullable((Integer) values.get("start"))
        );
    }

    public Filters getFilters() {
        return filters;
    }

    public Order getOrder() {
        return order;
    }

    public Optional<Integer> getLimit() {
        return limit;
    }

    public Optional<Integer> getStart() {
        return start;
    }

}
