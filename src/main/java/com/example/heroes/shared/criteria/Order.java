package com.example.heroes.shared.criteria;

public final class Order {

    private final OrderBy orderBy;
    private final OrderType orderType;

    public Order(OrderBy orderBy, OrderType orderType) {
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public String getOrderBy() {
        return orderBy.value();
    }

    public String getOrderType() {
        return orderType.value();
    }

    public String serialize() {
        return String.format("%s.%s", orderBy.value(), orderType.value());
    }
}
