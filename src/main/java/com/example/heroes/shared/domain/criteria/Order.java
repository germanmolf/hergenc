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

    public static Order fromValues(HashMap<String, Serializable> values) {
        String orderBY = (String) values.get("orderBy");
        if (orderBY == null || orderBY.isBlank()) {
            return Order.none();
        }
        return new Order(new OrderBy(orderBY), OrderType.fromValue((String) values.get("orderType")));
    }

    public String getOrderBy() {
        return orderBy.value();
    }

    public String getOrderType() {
        return orderType.value();
    }

    private static Order none() {
        return new Order(new OrderBy(""), OrderType.NONE);
    }
}
