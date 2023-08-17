package com.example.heroes.shared.domain.criteria;

import java.io.Serializable;
import java.util.HashMap;

public final class Order {

    private final OrderBy orderBy;
    private final OrderType orderType;

    public Order(OrderBy orderBy, OrderType orderType) {
        this.orderBy = orderBy;
        this.orderType = orderType;
    }

    public Order(String orderBy, String orderType) {
        this.orderBy = new OrderBy(orderBy);
        this.orderType = OrderType.valueOf(orderType.toUpperCase());
    }

    public static Order fromValues(HashMap<String, Serializable> values) {
        String orderBy = (String) values.get("orderBy");
        if (orderBy == null || orderBy.isBlank()) {
            return Order.none();
        }
        String orderType = (String) values.get("orderType");
        return new Order(new OrderBy(orderBy), OrderType.fromValue(orderType.toUpperCase()));
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
        return new Order(new OrderBy(""), OrderType.NONE);
    }
}
