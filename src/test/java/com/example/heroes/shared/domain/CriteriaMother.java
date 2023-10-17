package com.example.heroes.shared.domain;

import java.util.HashMap;
import java.util.Map;

public final class CriteriaMother {

    public static final String filterString = String.join("&", "filters.0.field=%s", "filters.0.operator=%s", "filters.0.value=%s");
    public static final String orderString = "orderBy=%s&orderType=%s";

    public static Map<String, String> createParamsWithFilter(String field, String operator, String value) {
        Map<String, String> params = new HashMap<>();
        params.put("filters.0.field", field);
        params.put("filters.0.operator", operator);
        params.put("filters.0.value", value);
        return params;
    }

    public static Map<String, String> createEmptyParams() {
        return new HashMap<>();
    }

    public static Map<String, String> createParamsWithFilterWithNoField(String operator, String value) {
        Map<String, String> params = new HashMap<>();
        params.put("filters.0.operator", operator);
        params.put("filters.0.value", value);
        return params;
    }

    public static Map<String, String> createParamsWithFilterWithNoValue(String field, String operator) {
        Map<String, String> params = new HashMap<>();
        params.put("filters.0.field", field);
        params.put("filters.0.operator", operator);
        return params;
    }

    public static Map<String, String> createParamsWithOrder(String orderBy, String orderType) {
        Map<String, String> params = new HashMap<>();
        params.put("orderBy", orderBy);
        params.put("orderType", orderType);
        return params;
    }

    public static Map<String, String> createParamsWithLimit(String limit) {
        Map<String, String> params = new HashMap<>();
        params.put("limit", limit);
        return params;
    }

    public static Map<String, String> createParamsWithStart(String start) {
        Map<String, String> params = new HashMap<>();
        params.put("start", start);
        return params;
    }

    public static Map<String, String> createParamsWithDefaultFilter(String field, String value) {
        Map<String, String> params = new HashMap<>();
        params.put(field, value);
        return params;
    }
}
