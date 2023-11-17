package com.example.heroes.versus.infraestructure.controller;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.shared.infraestructure.controller.CriteriaParser;
import com.example.heroes.versus.application.find.VersusFinder;
import com.example.heroes.versus.application.find.VersusResponse;
import com.example.heroes.versus.application.find.VersusSearcher;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/versus")
public final class VersusGetController {

    private final VersusFinder finder;
    private final VersusSearcher searcher;
    private final CriteriaParser criteriaParser;
    private static final HashSet<String> orderFields = new HashSet<>() {{
        add("heroId");
        add("villainId");
        add("defeated");
    }};
    private static final HashSet<String> filterFields = new HashSet<>() {{
        add("heroId");
        add("villainId");
        add("defeated");
    }};

    public VersusGetController(VersusFinder finder, VersusSearcher searcher) {
        this.finder = finder;
        this.searcher = searcher;
        this.criteriaParser = new CriteriaParser(filterFields, orderFields);
    }

    @GetMapping
    public List<VersusResponse> getVersus(@RequestParam Map<String, String> params) {
        Criteria criteria = criteriaParser.fromParams(params);
        return searcher.search(criteria);
    }

    @GetMapping("/{id}")
    public VersusResponse getVersus(@PathVariable String id) {
        return finder.find(id);
    }
}
