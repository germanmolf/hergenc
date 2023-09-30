package com.example.heroes.heroes.infraestructure.controller;

import com.example.heroes.heroes.application.find.HeroFinder;
import com.example.heroes.heroes.application.find.HeroResponse;
import com.example.heroes.heroes.application.find.HeroesSearcher;
import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.controller.CriteriaParser;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/heroes")
public final class HeroesGetController {

    private final HeroFinder finder;
    private final HeroesSearcher searcher;
    private final CriteriaParser criteriaParser;
    private static final HashSet<String> orderFields = new HashSet<>() {{
        add("name");
        add("power");
    }};
    private static final HashSet<String> filterFields = new HashSet<>() {{
        add("name");
        add("power");
    }};

    public HeroesGetController(HeroFinder finder, HeroesSearcher searcher) {
        this.finder = finder;
        this.searcher = searcher;
        this.criteriaParser = new CriteriaParser(filterFields, orderFields);
    }

    @GetMapping
    public List<HeroResponse> getHeroes(@RequestParam Map<String, String> params) {
        Criteria criteria = criteriaParser.fromParams(params);
        return searcher.search(criteria);
    }

    @GetMapping("/{id}")
    public HeroResponse getHero(@PathVariable String id) {
        return finder.find(id);
    }
}
