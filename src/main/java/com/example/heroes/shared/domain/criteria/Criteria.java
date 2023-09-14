package com.example.heroes.shared.domain.criteria;

import java.util.ArrayList;
import java.util.List;

public final class Criteria {

    private List<Filter> filters = new ArrayList<>();
    private Order order = Order.none();
    private Integer limit = Integer.MAX_VALUE;
    private Integer start = 0;

    public List<Filter> getFilters() {
        return filters;
    }

    public Criteria setFilters(List<Filter> filters) {
        this.filters = filters;
        return this;
    }

    public Order getOrder() {
        return order;
    }

    public Criteria setOrder(String orderBy, String orderType) {
        this.order = new Order(orderBy, orderType);
        return this;
    }

    public Integer getLimit() {
        return limit;
    }

    public Criteria setLimit(Integer limit) {
        this.limit = limit;
        return this;
    }

    public Integer getStart() {
        return start;
    }

    public Criteria setStart(Integer start) {
        this.start = start;
        return this;
    }

}
