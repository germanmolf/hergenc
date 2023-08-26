package com.example.heroes.villains.application.create;

public final class CreateVillainRequest {

    private final String id;
    private final String name;

    public CreateVillainRequest(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
