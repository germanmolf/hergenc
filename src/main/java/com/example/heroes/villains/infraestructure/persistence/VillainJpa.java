package com.example.heroes.villains.infraestructure.persistence;

import com.example.heroes.heroes.domain.HeroId;
import com.example.heroes.shared.infraestructure.persistence.ListToJsonConverter;
import com.example.heroes.villains.domain.Villain;
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
@Table(name = "villain")
public final class VillainJpa {

    @Id
    private String id;
    private String name;
    private String power;
    @Column(columnDefinition = "json")
    @Convert(converter = ListToJsonConverter.class)
    private List<String> villainsDefeated;
    private Integer villainsDefeatedTotal;
    private String status;
    private String villainDefeater;

    public static VillainJpa fromAggregate(Villain villain) {
        return new VillainJpa(villain.id().value(),
                villain.name().value(),
                villain.power().value(),
                villain.heroesDefeated().stream().map(HeroId::value).toList(),
                villain.heroesDefeatedTotal().value(),
                villain.status().value(),
                villain.heroDefeater().map(HeroId::value).orElse(null));
    }

    public Villain toAggregate() {
        return new Villain(id, name, power, villainsDefeated, villainsDefeatedTotal, status,
                Optional.ofNullable(villainDefeater));
    }
}

