package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.application.find.HeroResponse;
import com.example.heroes.heroes.application.find.HeroSearcher;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.controller.CriteriaParser;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/heroes")
public final class HeroGetController {

    private final HeroFinder finder;
    private final HeroSearcher searcher;
    private final CriteriaParser criteriaParser;
    private static final HashSet<String> filterFields = new HashSet<>() {{
        add("name");
        add("power");
        add("villainsDefeatedTotal");
        add("status");
        add("villainDefeater");
    }};
    private static final HashSet<String> orderFields = new HashSet<>() {{
        add("name");
        add("power");
        add("villainsDefeatedTotal");
        add("status");
    }};

    public HeroGetController(HeroFinder finder, HeroSearcher searcher) {
        this.finder = finder;
        this.searcher = searcher;
        this.criteriaParser = new CriteriaParser(filterFields, orderFields);
    }

    @GetMapping
    public ResponseEntity<List<HeroResponse>> getHeroes(@RequestParam Map<String, String> params) {
        Criteria criteria = criteriaParser.fromParams(params);
        var heroes = searcher.search(criteria);
        return ResponseEntity.ok(heroes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HeroResponse> getHero(@PathVariable String id) {
        var hero = finder.find(id);
        return ResponseEntity.ok(hero);
    }
}
