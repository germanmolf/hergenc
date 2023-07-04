package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.HeroFinder;
import com.example.heroes.heroes.application.HeroResponse;
import com.example.heroes.heroes.application.HeroesSearcher;
import com.example.heroes.shared.domain.criteria.Criteria;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/heroes")
public final class HeroesGetController {

    private final HeroFinder finder;
    private final HeroesSearcher searcher;

    public HeroesGetController(HeroFinder finder, HeroesSearcher searcher) {
        this.finder = finder;
        this.searcher = searcher;
    }

    @GetMapping
    public List<HeroResponse> getHeroes(@RequestParam HashMap<String, Serializable> params) {
        return searcher.search(Criteria.fromValues(params));
    }

    @GetMapping("/{id}")
    public HeroResponse getHero(@PathVariable String id) {
        return finder.find(id);
    }
}
