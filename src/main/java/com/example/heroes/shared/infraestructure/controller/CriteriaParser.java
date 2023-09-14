package com.example.heroes.shared.infraestructure.controller;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.domain.criteria.Filter;
import org.apache.logging.log4j.util.Strings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class CriteriaParser {

    private CriteriaParser() {
    }

    public static Criteria fromParams(Map<String, String> params) {
        Criteria criteria = new Criteria();
        extractFilters(criteria, params);
        extractOrder(criteria, params);
        extractLimit(criteria, params);
        extractStart(criteria, params);
        return criteria;
    }

    private static void extractFilters(Criteria criteria, Map<String, String> params) {
        int maxParams = params.size();
        List<Filter> filters = new ArrayList<>();

        for (int possibleKey = 0; possibleKey < maxParams; possibleKey++) {
            if (!params.containsKey(String.format("filters.%s.field", possibleKey))
                    || !params.containsKey(String.format("filters.%s.value", possibleKey))) {
                continue;
            }
            String field = params.get(String.format("filters.%s.field", possibleKey));
            String operator = params.getOrDefault(String.format("filters.%s.operator", possibleKey), Strings.EMPTY);
            String value = params.get(String.format("filters.%s.value", possibleKey));
            if (field.isBlank() || value.isBlank()) {
                continue;
            }
            Filter filter = new Filter(field, operator, value);
            filters.add(filter);
        }

        criteria.setFilters(filters);
    }

    private static void extractOrder(Criteria criteria, Map<String, String> params) {
        String orderBy = params.get("orderBy");
        if (orderBy == null || orderBy.isBlank()) {
            return;
        }
        String orderType = params.getOrDefault("orderType", Strings.EMPTY);
        criteria.setOrder(orderBy, orderType);
    }

    private static void extractLimit(Criteria criteria, Map<String, String> params) {
        String limit = params.get("limit");
        if (limit == null || limit.isBlank()) {
            return;
        }
        try {
            criteria.setLimit(Integer.valueOf(limit));
        } catch (NumberFormatException ignored) {
        }
    }

    private static void extractStart(Criteria criteria, Map<String, String> params) {
        String start = params.get("start");
        if (start == null || start.isBlank()) {
            return;
        }
        try {
            criteria.setStart(Integer.valueOf(start));
        } catch (NumberFormatException ignored) {
        }
    }
}
