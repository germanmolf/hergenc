package com.example.heroes.shared.domain.criteria;

import java.util.HashMap;

public final class Filter {

    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;

    public Filter(String field, String operator, String value) {
        this.field = new FilterField(field);
        this.operator = FilterOperator.fromValue(operator.toUpperCase());
        this.value = new FilterValue(value);
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return new Filter(
                values.get("field"),
                values.get("operator").toUpperCase(),
                values.get("value")
        );
    }

    public String getField() {
        return field.value();
    }

    public FilterOperator getOperator() {
        return operator;
    }

    public String getValue() {
        return value.value();
    }

}
