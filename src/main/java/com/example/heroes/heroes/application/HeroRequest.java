package com.example.heroes.heroes.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public final class HeroRequest {

    private final String id;
    private final String name;
    private final String power;
    
}
