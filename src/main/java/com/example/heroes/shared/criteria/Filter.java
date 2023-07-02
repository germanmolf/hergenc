package com.example.heroes.shared.criteria;

import java.util.HashMap;

public final class Filter {

    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;

    public Filter(FilterField field, FilterOperator operator, FilterValue value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public Filter(String field, String operator, String value) {
        this.field = new FilterField(field);
        this.operator = FilterOperator.fromValue(operator);
        this.value = new FilterValue(value);
    }

    public static Filter fromValues(HashMap<String, String> values) {
        return new Filter(
                values.get("field"),
                values.get("operator"),
                values.get("value")
        );
    }

    public String getField() {
        return field.value();
    }

    public String getOperator() {
        return operator.value();
    }

    public String getValue() {
        return value.value();
    }

}
