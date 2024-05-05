package germanmolf.hergenc.heroes.application.create;

import germanmolf.hergenc.heroes.domain.Hero;
import germanmolf.hergenc.heroes.domain.HeroIdMother;
import germanmolf.hergenc.heroes.domain.HeroNameMother;
import germanmolf.hergenc.heroes.domain.HeroPowerMother;
import germanmolf.hergenc.shared.domain.WordMother;
import org.apache.logging.log4j.util.Strings;

public final class CreateHeroRequestMother {

    private static CreateHeroRequest create(String id, String name, String power) {
        return new CreateHeroRequest(id, name, power);
    }

    public static CreateHeroRequest random() {
        return create(HeroIdMother.randomValue(), HeroNameMother.randomValue(), HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest fromAggregate(Hero hero) {
        return create(hero.id().value(), hero.name().value(), hero.power().value());
    }

    public static CreateHeroRequest withIdNull() {
        return create(null, HeroNameMother.randomValue(), HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest withIdNotValid() {
        return create("qwe", HeroNameMother.randomValue(), HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest withNameNull() {
        return create(HeroIdMother.randomValue(), null, HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest withNameBlank() {
        return create(HeroIdMother.randomValue(), Strings.EMPTY, HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest withNameOverLength() {
        return create(HeroIdMother.randomValue(), WordMother.randomMin(21), HeroPowerMother.randomValue());
    }

    public static CreateHeroRequest withPowerNull() {
        return create(HeroIdMother.randomValue(), HeroNameMother.randomValue(), null);
    }

    public static CreateHeroRequest withPowerBlank() {
        return create(HeroIdMother.randomValue(), HeroNameMother.randomValue(), Strings.EMPTY);
    }

    public static CreateHeroRequest withPowerOverLength() {
        return create(HeroIdMother.randomValue(), HeroNameMother.randomValue(), WordMother.randomMin(31));
    }
}
