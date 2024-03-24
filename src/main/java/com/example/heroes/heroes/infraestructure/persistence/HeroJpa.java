package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.shared.infraestructure.persistence.ListToJsonConverter;
import com.example.heroes.villains.domain.VillainId;
import jakarta.persistence.*;
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
    @Column(columnDefinition = "json")
    @Convert(converter = ListToJsonConverter.class)
    private List<String> villainsDefeated;
    private String status;
    private String villainDefeater;

    public static HeroJpa fromAggregate(Hero hero) {
        return new HeroJpa(hero.id().value(),
                hero.name().value(),
                hero.power().value(),
                hero.villainsDefeatedTotal().value(),
                hero.villainsDefeated().stream().map(VillainId::value).toList(),
                hero.status().value(),
                hero.villainDefeater().map(VillainId::value).orElse(null));
    }

    public Hero toAggregate() {
        return new Hero(id, name, power, villainsDefeatedTotal, villainsDefeated, status,
                Optional.ofNullable(villainDefeater));
    }
}
