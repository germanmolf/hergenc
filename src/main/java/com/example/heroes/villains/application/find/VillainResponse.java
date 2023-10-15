package com.example.heroes.villains.application.find;

import com.example.heroes.villains.domain.Villain;

import java.util.Objects;

public final class VillainResponse {

    private final String id;
    private final String name;
    private final String power;

    public VillainResponse(String id, String name, String power) {
        this.id = id;
        this.name = name;
        this.power = power;
    }

    public static VillainResponse fromAggregate(Villain villain) {
        return new VillainResponse(villain.getId().value(), villain.getName().value(), villain.getPower().value());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        VillainResponse that = (VillainResponse) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(power, that.power);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power);
    }
}
