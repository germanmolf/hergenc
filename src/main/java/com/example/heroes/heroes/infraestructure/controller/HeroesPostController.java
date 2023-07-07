package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.HeroCreator;
import com.example.heroes.heroes.application.HeroRequest;
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
    public ResponseEntity<String> postHero(@RequestBody HeroRequest hero) {
        creator.create(hero.getId(), hero.getName(), hero.getPower());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
