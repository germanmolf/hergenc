package com.example.heroes.shared.domain.criteria;

public enum OrderType {

    ASC("asc"),
    DESC("desc"),
    NONE("none");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public static OrderType fromValue(String value) {
        return switch (value) {
            case "asc" -> OrderType.ASC;
            case "desc" -> OrderType.DESC;
            default -> OrderType.NONE;
        };
    }

    public boolean isAsc() {
        return this == OrderType.ASC;
    }

    public String value() {
        return type;
    }
}
