package com.example.heroes.villains.application.create;

public final class CreateVillainRequest {

    private final String id;
    private final String name;
    private final String power;

    public CreateVillainRequest(String id, String name, String power) {
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
