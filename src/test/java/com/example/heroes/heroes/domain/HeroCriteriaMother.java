package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.criteria.Filter;
import com.example.heroes.shared.domain.criteria.Filters;
import com.example.heroes.shared.domain.criteria.Order;

import java.util.Arrays;

public final class HeroCriteriaMother {

    public static Criteria nameAndPowerContains(String name, String power) {
        Filter nameFilter = new Filter("name", "contains", name);
        Filter powerFilter = new Filter("power", "contains", power);
        return new Criteria(new Filters(Arrays.asList(nameFilter, powerFilter)), Order.none());
    }
}
