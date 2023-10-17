package com.example.heroes.heroes.domain;

import com.example.heroes.shared.domain.UuidMother;

public final class HeroIdMother {

    public static HeroId random() {
        return new HeroId(UuidMother.random());
    }
    
}
