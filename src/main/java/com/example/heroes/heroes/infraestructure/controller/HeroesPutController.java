package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.HeroCreator;
import com.example.heroes.heroes.application.HeroRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/heroes")
public final class HeroesPutController {

    private final HeroCreator creator;

    public HeroesPutController(HeroCreator creator) {
        this.creator = creator;
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> putHero(@RequestParam String id, @RequestBody HeroRequest hero) {
        creator.create(id, hero.getName(), hero.getPower());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
