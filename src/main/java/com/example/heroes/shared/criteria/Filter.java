package com.example.heroes.shared.criteria;

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

    public String getField() {
        return field.value();
    }

    public String getOperator() {
        return operator.value();
    }

    public String getValue() {
        return value.value();
    }

    public String serialize() {
        return String.format("%s.%s.%s", field.value(), operator.value(), value.value());
    }
}
