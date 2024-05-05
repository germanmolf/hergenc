package germanmolf.hergenc.heroes.application.find;

import germanmolf.hergenc.heroes.HeroModuleTest;
import germanmolf.hergenc.shared.domain.criteria.Criteria;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public final class HeroSearcherTest extends HeroModuleTest {

    private HeroSearcher searcher;

    @BeforeEach
    protected void setUp() {
        super.setUp();
        searcher = new HeroSearcher(repository);
    }

    @Test
    void search_for_heroes() {
        searcher.search(new Criteria());
    }
}
