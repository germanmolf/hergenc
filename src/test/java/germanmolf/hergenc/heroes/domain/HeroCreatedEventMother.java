package germanmolf.hergenc.heroes.domain;

import germanmolf.hergenc.heroes.application.create.CreateHeroRequest;

public final class HeroCreatedEventMother {

    private static HeroCreatedEvent create(String id, String name, String power) {
        return new HeroCreatedEvent(id, name, power);
    }

    public static HeroCreatedEvent random() {
        return create(HeroIdMother.randomValue(), HeroNameMother.randomValue(),
                HeroPowerMother.randomValue());
    }

    public static HeroCreatedEvent fromAggregate(Hero hero) {
        return create(hero.id().value(), hero.name().value(), hero.power().value());
    }

    public static HeroCreatedEvent fromRequest(CreateHeroRequest request) {
        return create(request.id(), request.name(), request.power());
    }
}
