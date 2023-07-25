package com.example.heroes.heroes.infraestructure.persistence;

import com.example.heroes.heroes.domain.Hero;
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
@Table(name = HeroJpa.TABLE_NAME)
public final class HeroJpa {

    public final static String TABLE_NAME = "heroes";

    @Id
    private String id;
    private String name;
    private String power;

    public static HeroJpa fromAggregate(Hero hero) {
        return new HeroJpa(hero.getId(), hero.getName(), hero.getPower());
    }

    public static Hero fromJpaEntity(HeroJpa heroJpa) {
        return new Hero(heroJpa.getId(), heroJpa.getName(), heroJpa.getPower());
    }
}
