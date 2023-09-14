package com.example.heroes.shared.domain.criteria;

public final class Filter {

    private final FilterField field;
    private final FilterOperator operator;
    private final FilterValue value;

    public Filter(String field, String operator, String value) {
        this.field = new FilterField(field);
        this.operator = FilterOperator.fromValue(operator.toLowerCase());
        this.value = new FilterValue(value);
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
