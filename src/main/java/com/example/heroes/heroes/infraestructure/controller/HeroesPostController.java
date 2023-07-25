package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.create.CreateHeroRequest;
import com.example.heroes.heroes.application.create.HeroCreator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/heroes")
public final class HeroesPostController {

    private final HeroCreator creator;

    public HeroesPostController(HeroCreator creator) {
        this.creator = creator;
    }

    @PostMapping
    public ResponseEntity<String> postHero(@RequestBody CreateHeroRequest request) {
        creator.create(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
