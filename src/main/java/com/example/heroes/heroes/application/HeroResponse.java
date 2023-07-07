package com.example.heroes.heroes.application;

import com.example.heroes.heroes.domain.Hero;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class HeroResponse {

    private final String id;
    private final String name;
    private final String power;

    public static HeroResponse fromAggregate(Hero hero) {
        return new HeroResponse(hero.getId(), hero.getName(), hero.getPower());
    }

}
