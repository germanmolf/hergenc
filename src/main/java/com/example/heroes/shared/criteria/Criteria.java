package com.example.heroes.shared.criteria;

public final class Criteria {

    private final Filters filters;
    private final Order order;
    private final Integer limit;
    private final Integer offset;

    public Criteria(Filters filters, Order order, Integer limit, Integer offset) {
        this.filters = filters;
        this.order = order;
        this.limit = limit;
        this.offset = offset;
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

    public Integer getOffset() {
        return offset;
    }

    public String serialize() {
        return String.format(
                "%s~~%s~~%s~~%s",
                filters.serialize(),
                order.serialize(),
                offset,
                limit
        );
    }
}
