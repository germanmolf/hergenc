package com.example.heroes.villains.application.find;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.villains.VillainModuleTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class VillainSearcherTest extends VillainModuleTest {

    private VillainsSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        searcher = new VillainsSearcher(repository);
    }

    @Test
    void search_for_villains() {
        searcher.search(new Criteria());
    }
}
