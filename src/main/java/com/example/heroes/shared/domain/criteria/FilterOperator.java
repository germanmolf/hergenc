package com.example.heroes.shared.domain.criteria;

public enum FilterOperator {

    EQUAL("="),
    NOT_EQUAL("!="),
    GT(">"),
    LT("<"),
    CONTAINS("contains"),
    NOT_CONTAINS("not_contains");

    private final String operator;

    FilterOperator(String operator) {
        this.operator = operator;
    }

    public static FilterOperator fromValue(String value) {
        return switch (value) {
            case "!=" -> NOT_EQUAL;
            case ">" -> GT;
            case "<" -> LT;
            case "contains" -> CONTAINS;
            case "not_contains" -> NOT_CONTAINS;
            default -> EQUAL;
        };
    }

    public String value() {
        return operator;
    }
}
