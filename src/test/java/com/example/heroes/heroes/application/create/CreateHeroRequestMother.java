package com.example.heroes.heroes.application.create;

import com.example.heroes.heroes.domain.Hero;
import com.example.heroes.heroes.domain.HeroIdMother;
import com.example.heroes.heroes.domain.HeroNameMother;
import com.example.heroes.heroes.domain.HeroPowerMother;
import com.example.heroes.shared.domain.WordMother;
import org.apache.logging.log4j.util.Strings;

public final class CreateHeroRequestMother {

    private static CreateHeroRequest create(String id, String name, String power) {
        return new CreateHeroRequest(id, name, power);
    }

    public static CreateHeroRequest random() {
        return create(HeroIdMother.random().value(), HeroNameMother.random().value(), HeroPowerMother.random().value());
    }

    public static CreateHeroRequest fromAggregate(Hero hero) {
        return create(hero.id().value(), hero.name().value(), hero.power().value());
    }

    public static CreateHeroRequest withIdNull() {
        return create(null, HeroNameMother.random().value(), HeroPowerMother.random().value());
    }

    public static CreateHeroRequest withIdNotValid() {
        return create("qwe", HeroNameMother.random().value(), HeroPowerMother.random().value());
    }

    public static CreateHeroRequest withNameNull() {
        return create(HeroIdMother.random().value(), null, HeroPowerMother.random().value());
    }

    public static CreateHeroRequest withNameBlank() {
        return create(HeroIdMother.random().value(), Strings.EMPTY, HeroPowerMother.random().value());
    }

    public static CreateHeroRequest withNameOverLength() {
        return create(HeroIdMother.random().value(), WordMother.randomMin(21), HeroPowerMother.random().value());
    }

    public static CreateHeroRequest withPowerNull() {
        return create(HeroIdMother.random().value(), HeroNameMother.random().value(), null);
    }

    public static CreateHeroRequest withPowerBlank() {
        return create(HeroIdMother.random().value(), HeroNameMother.random().value(), Strings.EMPTY);
    }

    public static CreateHeroRequest withPowerOverLength() {
        return create(HeroIdMother.random().value(), HeroNameMother.random().value(), WordMother.randomMin(31));
    }
}
