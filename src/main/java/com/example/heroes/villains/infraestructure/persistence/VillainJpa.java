package com.example.heroes.villains.infraestructure.persistence;

import com.example.heroes.villains.domain.Villain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    public static VillainJpa fromAggregate(Villain villain) {
        return new VillainJpa(villain.getId().value(), villain.getName().value(), villain.getPower().value());
    }

    public static Villain fromJpaEntity(VillainJpa villainJpa) {
        return new Villain(villainJpa.getId(), villainJpa.getName(), villainJpa.getPower());
    }
}

