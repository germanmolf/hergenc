package com.example.heroes.shared.domain.criteria;

import org.apache.logging.log4j.util.Strings;

public final class Order {

    private final OrderBy orderBy;
    private final OrderType orderType;

    public Order(String orderBy, String orderType) {
        this.orderBy = new OrderBy(orderBy);
        this.orderType = OrderType.fromValue(orderType.toLowerCase());
    }

    private Order(OrderBy orderBy, OrderType orderType) {
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public String getOrderBy() {
        return orderBy.value();
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public boolean hasOrder() {
        return orderType != OrderType.NONE;
    }

    public static Order none() {
        return new Order(
                new OrderBy(Strings.EMPTY),
                OrderType.NONE);
    }
}
