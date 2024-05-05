package germanmolf.hergenc.shared.infraestructure.controller;

import germanmolf.hergenc.shared.domain.criteria.Criteria;
import germanmolf.hergenc.shared.domain.criteria.Filter;
import germanmolf.hergenc.shared.domain.criteria.FilterOperator;
import org.apache.logging.log4j.util.Strings;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

public final class CriteriaParser {

    private final Criteria criteria;
    private final HashSet<String> filterFields;
    private final HashSet<String> orderFields;

    public CriteriaParser(HashSet<String> filterFields, HashSet<String> orderFields) {
        this.filterFields = filterFields;
        this.orderFields = orderFields;
        criteria = new Criteria();
    }

    public Criteria fromParams(Map<String, String> params) {
        extractOrder(params);
        extractLimit(params);
        extractStart(params);
        extractDefaultFilters(params);
        extractFilters(params);
        return criteria;
    }

    private void extractOrder(Map<String, String> params) {
        String orderBy = params.remove("orderBy");
        if (orderBy == null || orderBy.isBlank()) {
            return;
        }
        if (!orderFields.contains(orderBy)) {
            return;
        }
        String orderType = params.getOrDefault("orderType", Strings.EMPTY);
        criteria.setOrder(orderBy, orderType);
    }

    private void extractLimit(Map<String, String> params) {
        String limit = params.remove("limit");
        if (limit == null || limit.isBlank()) {
            return;
        }
        try {
            criteria.setLimit(Integer.valueOf(limit));
        } catch (NumberFormatException ignored) {
        }
    }

    private void extractStart(Map<String, String> params) {
        String start = params.remove("start");
        if (start == null || start.isBlank()) {
            return;
        }
        try {
            criteria.setStart(Integer.valueOf(start));
        } catch (NumberFormatException ignored) {
        }
    }

    private void extractDefaultFilters(Map<String, String> params) {
        List<Filter> filters = criteria.getFilters();
        for (String filterField : filterFields) {
            String value = params.remove(filterField);
            if (value == null || value.isBlank()) {
                continue;
            }
            Filter filter = new Filter(filterField, FilterOperator.EQUAL.value(), value);
            filters.add(filter);
        }
        criteria.setFilters(filters);
    }

    private void extractFilters(Map<String, String> params) {
        int maxParams = params.size();
        List<Filter> filters = criteria.getFilters();

        for (int possibleKey = 0; possibleKey < maxParams; possibleKey++) {
            if (!containsFieldAndValue(params, possibleKey)) {
                continue;
            }
            String field = params.get(String.format("filters[%s][field]", possibleKey));
            if (!filterFields.contains(field)) {
                continue;
            }
            String operator = params.getOrDefault(String.format("filters[%s][operator]", possibleKey), Strings.EMPTY);
            String value = params.get(String.format("filters[%s][value]", possibleKey));
            if (field.isBlank() || value.isBlank()) {
                continue;
            }
            Filter filter = new Filter(field, operator, value);
            filters.add(filter);
        }

        criteria.setFilters(filters);
    }

    private static boolean containsFieldAndValue(Map<String, String> params, int possibleKey) {
        return params.containsKey(String.format("filters[%s][field]", possibleKey)) && params.containsKey(String.format("filters[%s][value]", possibleKey));
    }
}
