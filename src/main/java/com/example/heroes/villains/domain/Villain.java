package com.example.heroes.villains.domain;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.domain.AggregateRoot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class Villain extends AggregateRoot {

    private final VillainId id;
    private final VillainName name;
    private final VillainPower power;
    private final List<HeroId> heroesDefeated;
    private VillainHeroesDefeatedTotal heroesDefeatedTotal;

    public Villain(String id, String name, String power) {
        this.id = new VillainId(id);
        this.name = new VillainName(name);
        this.power = new VillainPower(power);
        this.heroesDefeated = new ArrayList<>();
        this.heroesDefeatedTotal = VillainHeroesDefeatedTotal.initialize();
    }

    public Villain(String id, String name, String power, List<String> heroesDefeated, Integer heroesDefeatedTotal) {
        this.id = new VillainId(id);
        this.name = new VillainName(name);
        this.power = new VillainPower(power);
        this.heroesDefeated = heroesDefeated.stream().map(HeroId::new).collect(Collectors.toList());
        this.heroesDefeatedTotal = new VillainHeroesDefeatedTotal(heroesDefeatedTotal);
    }

    public static Villain create(String id, String name, String power) {
        Villain villain = new Villain(id, name, power);
        villain.record(new VillainCreatedEvent(id, name, power));
        return villain;
    }

    public boolean hasDefeated(HeroId heroId) {
        return heroesDefeated.contains(heroId);
    }

    public void addHeroDefeated(HeroId heroId) {
        heroesDefeatedTotal = heroesDefeatedTotal.increment();
        heroesDefeated.add(heroId);
    }

    public VillainId id() {
        return id;
    }

    public VillainName name() {
        return name;
    }

    public VillainPower power() {
        return power;
    }

    public List<HeroId> heroesDefeated() {
        return heroesDefeated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Villain villain = (Villain) o;
        return Objects.equals(id, villain.id) && Objects.equals(name, villain.name) && Objects.equals(power, villain.power) && Objects.equals(heroesDefeated, villain.heroesDefeated) && Objects.equals(heroesDefeatedTotal, villain.heroesDefeatedTotal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, power, heroesDefeated, heroesDefeatedTotal);
    }
}
