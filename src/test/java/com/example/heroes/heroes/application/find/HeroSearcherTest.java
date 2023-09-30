package com.example.heroes.heroes.application.find;

import com.example.heroes.heroes.HeroModuleTest;
import com.example.heroes.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class HeroSearcherTest extends HeroModuleTest {

    private HeroesSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        searcher = new HeroesSearcher(repository);
    }

    @Test
    void search_for_heroes() {
        searcher.search(new Criteria());
    }
}
