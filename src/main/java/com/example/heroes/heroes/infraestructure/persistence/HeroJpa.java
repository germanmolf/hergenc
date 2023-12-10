package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.villains.domain.VillainId;
import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

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
    @Type(JsonType.class)
    @Column(columnDefinition = "json")
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
        return new Hero(id, name, power, villainsDefeatedTotal, villainsDefeated, status, Optional.ofNullable(villainDefeater));
    }
}
