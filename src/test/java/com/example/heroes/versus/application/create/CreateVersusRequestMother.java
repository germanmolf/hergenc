package com.example.heroes.versus.application.create;

import com.example.heroes.shared.domain.UuidMother;

import java.util.Random;

//TODO que no pueda instanciarse
public final class CreateVersusRequestMother {

    private static CreateVersusRequest create(String id, String heroId, String villainId, String defeated) {
        return new CreateVersusRequest(id, heroId, villainId, defeated);
    }

    public static CreateVersusRequest random() {
        String[] s = {"hero", "villain", "both", "none"};
        Random ran = new Random();
        String s_ran = s[ran.nextInt(s.length)];
        return create(UuidMother.random(), UuidMother.random(), UuidMother.random(), s_ran);
    }

    public static CreateVersusRequest withDefeatedNull() {
        return create(UuidMother.random(), UuidMother.random(), UuidMother.random(), null);
    }

    public static CreateVersusRequest withDefeatedNotValid() {
        return create(UuidMother.random(), UuidMother.random(), UuidMother.random(),
                "value_not_valid");
    }

    public static CreateVersusRequest withHeroDefeated() {
        String[] s = {"hero", "both"};
        Random ran = new Random();
        String s_ran = s[ran.nextInt(s.length)];
        return create(UuidMother.random(), UuidMother.random(), UuidMother.random(), s_ran);
    }

    public static CreateVersusRequest withVillainDefeated() {
        String[] s = {"villain", "both"};
        Random ran = new Random();
        String s_ran = s[ran.nextInt(s.length)];
        return create(UuidMother.random(), UuidMother.random(), UuidMother.random(), s_ran);
    }
}
