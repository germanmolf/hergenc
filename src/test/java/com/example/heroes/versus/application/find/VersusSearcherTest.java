package com.example.heroes.versus.application.find;

import com.example.heroes.shared.domain.criteria.Criteria;
import com.example.heroes.versus.application.VersusModuleTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class VersusSearcherTest extends VersusModuleTest {

    private VersusSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        searcher = new VersusSearcher(repository);
    }

    @Test
    void search_for_versus() {
        searcher.search(new Criteria());
    }
}
