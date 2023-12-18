package com.example.heroes.villains.domain;

public enum VillainStatus {

    ACTIVE("active"),
    DEFEATED("defeated");

    private final String status;

    VillainStatus(String status) {
        this.status = status;
    }

    public static VillainStatus fromValue(String value) {
        return switch (value) {
            case "active" -> ACTIVE;
            case "defeated" -> DEFEATED;
            default -> throw new IllegalStateException("Unexpected value: " + value);
        };
    }

    public String value() {
        return status;
    }

    public boolean isDefeated() {
        return this == DEFEATED;
    }
}
