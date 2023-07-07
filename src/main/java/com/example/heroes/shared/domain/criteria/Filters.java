package com.example.heroes.shared.domain.criteria;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public final class Filters {

    private final List<Filter> filters;

    public Filters(List<Filter> filters) {
        this.filters = filters;
    }

    public static Filters fromValues(HashMap<String, Serializable> values) {
        List<HashMap<String, String>> filters = (List<HashMap<String, String>>) values.get("filters");
        if (filters == null) {
            filters = new ArrayList<>();
        }
        return new Filters(filters.stream().map(Filter::fromValues).collect(Collectors.toList()));
    }

    public List<Filter> getFilters() {
        return filters;
    }

}