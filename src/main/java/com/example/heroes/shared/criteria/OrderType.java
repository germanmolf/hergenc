package com.example.heroes.shared.criteria;

public enum OrderType {

    ASC("asc"),
    DESC("desc"),
    NONE("none");

    private final String type;

    OrderType(String type) {
        this.type = type;
    }

    public static OrderType fromValue(String value) {
        switch (value) {
            case "asc":
                return OrderType.ASC;
            case "desc":
                return OrderType.DESC;
            default:
                return OrderType.NONE;
        }
    }

    public String value() {
        return type;
    }
}
