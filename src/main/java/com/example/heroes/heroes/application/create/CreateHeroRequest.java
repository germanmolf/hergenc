package com.example.heroes.heroes.application.create;

public final class CreateHeroRequest {

    private final String id;
    private final String name;
    private final String power;

    public CreateHeroRequest(String id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPower() {
        return power;
    }
}
