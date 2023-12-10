package com.example.heroes.heroes.domain;

public enum HeroStatus {

    ACTIVE("active"),
    DEFEATED("defeated");

    private final String status;

    HeroStatus(String status) {
        this.status = status;
    }

    public static HeroStatus fromValue(String value) {
        return switch (value) {
            case "active" -> ACTIVE;
            case "defeated" -> DEFEATED;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    public String value() {
        return status;
    }
}
