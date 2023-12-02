package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.villains.domain.VillainId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
@Table(name = "hero")
public final class HeroJpa {

    @Id
    private String id;
    private String name;
    private String power;
    private Integer villainsDefeatedTotal;
    private List<VillainId> villainsDefeated;
    private String status;
    private String villainDefeater;

    public static HeroJpa fromAggregate(Hero hero) {
        return new HeroJpa(hero.id().value(),
                hero.name().value(),
                hero.power().value(),
                hero.villainsDefeatedTotal(),
                hero.villainsDefeated(),
                hero.status(),
                hero.villainDefeater().map(VillainId::value).orElse(null));
    }

    public Hero toAggregate() {
        return new Hero(id, name, power, villainsDefeatedTotal, villainsDefeated, status, Optional.of(villainDefeater));
    }
}
